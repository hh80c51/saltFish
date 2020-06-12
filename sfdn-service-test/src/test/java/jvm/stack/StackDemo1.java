package jvm.stack;

/**
 * @Description: 局部变量的线程安全问题
 * @Auther: Administrator
 * @Date: 2020/6/11 22:20
 */
public class StackDemo1 {
    //多个线程同时调用此方法，每个线程有自己独立的栈帧，都有自己私有的局部变量x，互不影响
    static void m1(){
        int x = 0;
        for (int i = 0; i < 5000; i++) {
            x++;
        }
        System.out.println(x);
    }
}
