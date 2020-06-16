package jvm.classLoad;

/**
 * @author hh
 * @description 完成懒惰初始化单例模式
 * @date 2020/6/16  17:48
 */
public class LoadDemo4 {
    public static void main(String[] args) {
        //此时不会初始化Singleton，除非调用getInstance()方法
//        Singleton.test();
        Singleton.getInstance();
    }
}

class Singleton {

    public static void test() {
        System.out.println("test");
    }

    private Singleton() {}

    //类初始化操作是线程安全的，由类加载器保证了线程的安全性
    private static class LazyHolder{
        private static final Singleton SINGLETON = new Singleton();
        static {
            System.out.println("lazy holder init");
        }
    }

    public static Singleton getInstance() {
        return LazyHolder.SINGLETON;
    }
}