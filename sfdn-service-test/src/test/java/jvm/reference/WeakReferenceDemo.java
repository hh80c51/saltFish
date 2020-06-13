package jvm.reference;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 演示弱引用
 * @Auther: Administrator
 * @Date: 2020/6/13 23:22
 */
public class WeakReferenceDemo {
    private static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) {

        //list ----> WeakReference -->byte[]
        List<WeakReference<byte[]>> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            WeakReference<byte[]> ref = new WeakReference<byte[]>(new byte[_4MB]);
            list.add(ref);
            //打印有多少个弱引用对象
            //第10次也发生了Full GC，说明弱引用对象自身也占用一定内存
            for (WeakReference<byte[]> w : list) {
                System.out.print(w.get()+ " ");
            }
            System.out.println();
        }
        System.out.println("循环结束：" + list.size());
    }
}
