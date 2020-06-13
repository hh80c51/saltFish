package jvm.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 演示软引用，配合引用队列
 * 清理软无用的引用对象
 * @Auther: Administrator
 * @Date: 2020/6/13 23:15
 */
public class SoftReferenceDemo2 {
    private static final int _4MB = 4 * 1024 * 1024;

    public static void main(String[] args) {
        //list ----> SoftReference -->byte[]
        List<SoftReference<byte[]>> list = new ArrayList<>();

        //引用队列
        ReferenceQueue<byte[]> queue = new ReferenceQueue<>();

        for (int i = 0; i < 5; i++) {
            //关联了引用队列，当软引用所关联的byte数组被回收时，软引用自己会加入到queue中去
            SoftReference<byte[]> ref = new SoftReference<byte[]>(new byte[_4MB], queue);
            System.out.println(ref.get());
            list.add(ref);
            System.out.println(list.size());
        }

        //从队列中获取无用的 软引用对象，并移除
        Reference<? extends byte[]> poll = queue.poll();
        while (poll != null){
            list.remove(poll);
            poll = queue.poll();
        }

        for (SoftReference<byte[]> ref : list) {
            System.out.println(ref.get());
        }
    }
}
