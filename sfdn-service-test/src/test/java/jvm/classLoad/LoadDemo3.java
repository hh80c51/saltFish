package jvm.classLoad;

/**
 * @author hh
 * @description
 * @date 2020/6/16  17:18
 */
public class LoadDemo3 {
    static {
        System.out.println("main init");
    }

    public static void main(String[] args) throws ClassNotFoundException {
        //1.静态常量不会触发初始化，准备阶段赋值
        System.out.println(B.b);
        //2.类对象.class不会触发初始化
        System.out.println(B.class);
        //3.创建该类的数组不会触发初始化
        System.out.println(new B[0]);
        //4.不会初始化类B 但会加载B、A
        ClassLoader c1 = Thread.currentThread().getContextClassLoader();
        c1.loadClass("jvm.classLoad.B");
        //5.不会初始化类B 但会加载B、A
        ClassLoader c2 = Thread.currentThread().getContextClassLoader();
        Class.forName("jvm.classLoad.B", false, c2);
    }
}

class A {
    static int a = 0;
    static {
        System.out.println("a init");
    }
}

class B extends A {
    final static double b = 5.0;
    static boolean c = false;
    static {
        System.out.println("b init");
    }
}
