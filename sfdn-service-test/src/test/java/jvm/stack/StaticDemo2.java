package jvm.stack;

/**
 * @Description: 局部变量的线程安全问题
 * @Auther: Administrator
 * @Date: 2020/6/11 22:23
 */
public class StaticDemo2 {
    public static void main(String[] args) {

        //方法2演示：此时主线程和新开线程都在修改同一个StringBuilder对象
        StringBuilder sb = new StringBuilder();
        sb.append(4);
        sb.append(5);
        sb.append(6);
        new Thread(()->{
            m2(sb);
        }).start();
    }

    //没有线程安全问题
    public static void m1() {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }

    //存在线程安全问题，sb逃逸出此方法
    public static void m2(StringBuilder sb) {
        sb.append(1);
        sb.append(2);
        sb.append(3);
        System.out.println(sb.toString());
    }

    //存在线程安全问题
    public static StringBuilder m3() {
        StringBuilder sb = new StringBuilder();
        sb.append(1);
        sb.append(2);
        sb.append(3);
        return sb;
    }
}
