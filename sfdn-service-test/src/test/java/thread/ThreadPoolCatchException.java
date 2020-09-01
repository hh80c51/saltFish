package thread;

import freemarker.template.utility.DateUtil;

import java.util.Date;
import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * @author hh
 * @description
 * @date 2020/9/1  9:33
 */
public class ThreadPoolCatchException {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = new ThreadPoolExecutor(5, 10,
                                 30, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5, true),
                                 Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

//        for (int i = 0; i < 10; i++) {
//            final int index = i;
//            Future future = executorService.submit(new FutureTaskWithResult(index));
//            int result = (int) future.get();
//        }

        /**
         【thread-name:pool-1-thread-1，执行方式execute】
         【thread-name:pool-1-thread-2，执行方式execute】
         【thread-name:pool-1-thread-4，执行方式execute】
         【thread-name:pool-1-thread-6，执行方式execute】
         Exception in thread "pool-1-thread-3" java.lang.RuntimeException: 【thread-name:pool-1-thread-3，执行方式execute-exception】，我异常啦！哈哈哈！
         首先，一个线程异常不会影响其他线程的正常执行。那为什么3线程和5线程没有了？
         首先3线程是因为异常，被线程池移除，从workers中把异常的线程remote了，然后new Worker()，再把new出来的Worker，add到workers集合中
         由于是在多线程的环境下，线程3异常后，调用addWorker的时机先于任务5addWorker的时机，所以，第五个线程就是执行线程3异常之后创建的线程，即线程6。
         */
        executorService.execute(()-> sayHi("execute"));
        executorService.execute(()-> sayHi("execute"));
        executorService.execute(()-> sayHi("execute-exception"));
        executorService.execute(()-> sayHi("execute"));
        executorService.execute(()-> sayHi("execute"));
//        executorService.submit(()-> sayHi("submit"));

    }

    private static void sayHi(String name){
        String printStr = "【thread-name:"+ Thread.currentThread().getName() + "，执行方式" +name + "】";
        if("execute-exception".equals(name)){
            throw new RuntimeException(printStr + "，我异常啦！哈哈哈！");
        } else {
            System.out.println(printStr);
        }
    }
}
class FutureTaskWithResult implements Callable {
    private int result;

    public FutureTaskWithResult(int result) {
        this.result = result;
    }

    @Override
    public Object call() throws Exception {
        System.out.println(new Date() + " " + Thread.currentThread().getName());
        //执行业务逻辑，返回执行结果
        if(result == 3){
            throw new RuntimeException("我异常啦，哈哈哈哈");
        }
        System.out.println("任务执行结果：" + result);
        return result;
    }
}
