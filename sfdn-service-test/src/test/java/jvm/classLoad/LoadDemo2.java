package jvm.classLoad;

import java.io.IOException;

/**
 * @author hh
 * @description
 * @date 2020/6/15  16:59
 */
public class LoadDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ClassLoader classLoader = LoadDemo2.class.getClassLoader();
        //loadClass 方法不会导致类的解析和初始化，D在jvm中找不到，说明未初始化，C未解析
//        Class<?> c = classLoader.loadClass("jvm.classLoad.C");

        new C();
        System.in.read();
    }
}

class C {
    D d = new D();
}

class D {

}
