package jvm.jmm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author hh
 * @description
 * @date 2020/6/17  16:28
 */
public class JUCDemo {
    private static AtomicInteger i = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i.getAndIncrement();    //获取并且自增 i++
//                i.incrementAndGet();  //自增且获取 ++i
            }
        });

        new StringBuffer().append("a").append("b").append("c");

        Thread t2 = new Thread(() -> {
            for (int j = 0; j < 5000; j++) {
                i.getAndDecrement();    //获取并且自减 i++
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println(i);
    }
}
