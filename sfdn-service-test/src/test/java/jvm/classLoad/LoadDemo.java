package jvm.classLoad;

/**
 * @author hh
 * @description 类加载Demo
 * static变量分配空间和赋值是两个步骤，分配空间在准备阶段完成，赋值在初始化阶段完成
 * 如果static变量是final的基本类型，那么编译阶段值就确定了，赋值在准备阶段完成
 * 如果static变量是final的，但属于引用类型，那么赋值也会在初始化阶段完成
 * javap -v -p LoadDemo.class
 * @date 2020/6/15  16:06
 */
public class LoadDemo {
    static int a;
    static int b = 10;
    static final int c = 20;
    static final String d = "hello";
    static final Object e = new Object();

//    public static void main(String[] args) {
//        System.out.println(LoadDemo.class.getResource("").getPath());
//    }
}
