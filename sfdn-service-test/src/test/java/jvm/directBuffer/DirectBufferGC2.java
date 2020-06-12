package jvm.directBuffer;

import sun.misc.Unsafe;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @Description: 直接内存底层如何释放内存
 * @Auther: Administrator
 * @Date: 2020/6/11 22:50
 */
public class DirectBufferGC2 {
    static int _1Gb = 1024 * 1024 *1024;

    public static void main(String[] args) throws IOException {
        //直接内存底层使用unsafe来分配内存，释放内存
        Unsafe unsafe = getUnsafe();
        //分配内存
        long base = unsafe.allocateMemory(_1Gb);
        unsafe.setMemory(base, _1Gb, (byte) 0);
        System.in.read();

        //释放内存
        unsafe.freeMemory(base);
        System.in.read();
    }

    //反射获取unsafe对象
    public static Unsafe getUnsafe(){
        try {
            Field f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            Unsafe unsafe = (Unsafe) f.get(null);
            return unsafe;
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException();
        }
    }
}
