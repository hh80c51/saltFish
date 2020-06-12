package jvm.directBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Description: 从window任务管理器监测直接内存
 * @Auther: Administrator
 * @Date: 2020/6/11 22:46
 */
public class DirectBufferGC {

    static int _1Gb = 1024 * 1024 *1024;

    /**
     * -XX:+DisableExplicitGC 禁止显式的垃圾回收之后，对直接内存的释放有影响
     * System.gc()不会进行垃圾回收，导致ByteBuffer对象不能被垃圾回收，就不能触发freeMemory来释放直接内存
     * 此时只能主动执行freeMemory释放直接内存
     */
    public static void main(String[] args) throws IOException {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_1Gb);
        System.out.println("分配完毕...");
        System.in.read();
        System.out.println("开始释放...");
        byteBuffer = null;
        System.gc();    //显式的垃圾回收
        System.in.read();

    }

}
