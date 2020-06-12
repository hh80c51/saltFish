package jvm.directBuffer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 演示直接内存和java缓冲区两种读取方式的效率对比
 */
public class DirectBufferDemo {
    static final String FROM = "F:\\影音\\TtsL95Pn.mp4";
    static final String TO = "E:\\a.mp4";
    static final int _1Mb = 1024 * 1024;

    public static void main(String[] args) {
        io();
        directBuffer();
    }

    private static void directBuffer(){
        long start = System.nanoTime();
        try (FileChannel from = new FileInputStream(FROM).getChannel();
            FileChannel to = new FileOutputStream(TO).getChannel();
        ){
            //使用ByteBuffer分配读写的缓冲区
            ByteBuffer bb = ByteBuffer.allocateDirect(_1Mb);
            while (true) {
                int len = from.read(bb);
                if (len == -1){
                    break;
                }
                bb.flip();
                to.write(bb);
                bb.clear();
            }

        }catch (IOException e){
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("directBuffer 用时："+ (end - start) / 1000_000.0);
    }

    private static void io() {
        long start = System.nanoTime();
        try (FileInputStream from = new FileInputStream(FROM);
             FileOutputStream to = new FileOutputStream(TO);
             ) {
            //分配了byte数组的缓冲区
            byte[] buf = new byte[_1Mb];
            while (true) {
                int len = from.read(buf);
                if (len == -1){
                    break;
                }
                to.write(buf,0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long end = System.nanoTime();
        System.out.println("io 用时："+ (end - start) / 1000_000.0);
    }
}
