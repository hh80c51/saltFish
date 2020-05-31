package redission;

import org.junit.Test;
import org.redisson.Config;
import org.redisson.RedissonClient;
import org.redisson.SingleServerConfig;
import org.redisson.core.RBucket;
import org.redisson.core.RList;
import org.redisson.core.RLock;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class RedissionTest {

    public volatile int num = 10;

        int threads = 5; // 工作线程数
        int nettyThreads = 4; // netty 线程数
        int interval = 2000; // 集群扫描间隔
        String redisKey = "TestLockKey";
        long releaseSeconds = 6000; // 锁自动释放时间
        long wailTime = 2000;// 等待时间

        RedissonClient redissonClient =  RedissionUtils.getInstance().getRedissionClient();

    @Test
    public void doubleUpdate() throws InterruptedException {
        Thread thread1 = new Thread(() -> {

            // 分布式锁
            RLock rLock = redissonClient.getLock(redisKey);
            boolean getLock = false;
            try {
                getLock = rLock.tryLock(wailTime, releaseSeconds, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("A尝试获取锁"+getLock);
            if (getLock){
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("A lock ok");
                num = 5;
                System.out.println("状态改成5");
                rLock.unlock();
                System.out.println("A unlock");
                System.out.println("A 执行结束");
            }
        });
        Thread thread2 = new Thread(() -> {
            while (num != 6){
                // 分布式锁
                RLock rLock = redissonClient.getLock(redisKey);
                boolean getLock = false;
                try {
                    getLock = rLock.tryLock(wailTime, releaseSeconds, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("B尝试获取锁"+getLock);
                if (getLock){
                    System.out.println("B lock ok");
                    num = 6;
                    System.out.println("状态改成6");
                    rLock.unlock();
                    System.out.println("B unlock");
                    System.out.println("B 执行结束");
                    return;
                }
            }

        });
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
        Thread.sleep(20000);
        System.out.println("num="+num);
    }

    @Test
    public void put(){
        Config config = new Config();
        SingleServerConfig singleSerververConfig = config.useSingleServer();
        singleSerververConfig.setAddress("127.0.0.1:6379");

        RedissonClient redissonClient = RedissionUtils.getInstance().getRedissionClient();

//        RBucket rBucket = redissonClient.getBucket("key");
//        rBucket.set("hh");
//        System.out.println(rBucket.get());

        RList rList = redissonClient.getList("cartProductIds");
        rList.add("123");
        rList.readAll().forEach(System.out::println);


    }

}