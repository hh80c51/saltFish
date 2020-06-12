package jvm.stack;

/**
 * @Description: 演示栈内存溢出，java.lang.StackOverflowError
 * 参数：-Xss256K
 * @Auther: Administrator
 * @Date: 2020/6/11 22:34
 */
public class StackOutOfMemory {
    private static int count;

    public static void main(String[] args) {
        try {
            method1();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void method1() {
        count++;
        method1();
    }
}
