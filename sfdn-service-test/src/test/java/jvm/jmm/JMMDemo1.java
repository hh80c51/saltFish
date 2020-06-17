package jvm.jmm;

/**
 * @author hh
 * @description 内存可见性演示
 * run加volatile，或者代码块加了println之后，线程才会停下来
 * @date 2020/6/17  10:34
 */
public class JMMDemo1 {
    static boolean run = true;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(()->{
            while (run){
                //...
                System.out.println(1);
            }
        });
        t.start();

        Thread.sleep(1000);
        run = false;
    }
}
