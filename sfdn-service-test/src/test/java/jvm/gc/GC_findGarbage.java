package jvm.gc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 可达性分析_根对象
 * jps
 * 转储jvm内存快照
 * jmap -dump:format=b,live,file=1.bin 进程id
 * @Auther: Administrator
 * @Date: 2020/6/12 22:35
 */
public class GC_findGarbage {
    public static void main(String[] args) throws IOException {
        List<Object> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("b");
        System.out.println(1);
        System.in.read();

        list1 = null;
        System.out.println(2);
        System.in.read();
        System.out.println("end...");
    }
}
