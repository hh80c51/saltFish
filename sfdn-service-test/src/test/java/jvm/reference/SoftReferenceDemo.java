package jvm.reference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 演示软引用
 * -Xmx20m -XX:+PrintGCDetails -verbose:gc
 * @Auther: Administrator
 * @Date: 2020/6/13 22:51
 */
public class SoftReferenceDemo {
    private static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) throws IOException {
        /**
         * list加byte数组
         * 模拟现实场景：byte数组可能是读取网络图片，放到list中，此时强引用导致内存溢出。
         */
        List<byte[]> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new byte[_4MB]);
        }
        System.in.read();
    }

    /**
     * @description list先引用软引用对象，软引用对象再引用byte数组
     * @param 
     * @return void
     * @date 2020/6/13 22:59
     * @author hh
     */
    public static void soft() {
        List<SoftReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            SoftReference<byte[]> ref = new SoftReference<byte[]>(new byte[_4MB]);
            System.out.println(ref.get());
            list.add(ref);
            //第四个对象加到list之后，堆满，触发垃圾回收之后，内存还是不足，开始释放软引用
            System.out.println(list.size());
            //只有最后一个对象保存了下来
        }
        System.out.println("循环结束：" + list.size());
        for (SoftReference<byte[]> ref : list) {
            System.out.println(ref.get());
        }
    }
}
