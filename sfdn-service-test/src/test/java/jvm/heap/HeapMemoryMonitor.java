package jvm.heap;

import java.util.ArrayList;
import java.util.List;

public class HeapMemoryMonitor {
    public static void main(String[] args) throws InterruptedException {
        //堆占用变化情况
//        heapChange();

        //jvirsualvm
        bigMemory();
    }

    /**
     * @description 三个时间段抓取heap占用情况
     *              jps找到当前进程id
     *              jmap -heap 进程id查看heap占用情况
     * @param
     * @return void
     * @date 2020/6/7 15:10
     * @author hh
     */
    private static void heapChange() throws InterruptedException {
        System.out.println("1...");
        Thread.sleep(30000);
        byte[] array = new byte[1024 * 1024 * 10];  //10Mb
        System.out.println("2...");
        Thread.sleep(20000);
        array = null;
        System.gc();
        System.out.println("3...");
        Thread.sleep(10000000L);
    }



    /**
     * @description jvirsualvm监测堆内大对象
     * @param
     * @return void
     * @date 2020/6/7 15:10
     * @author hh
     */
    private static void bigMemory() throws InterruptedException {
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            studentList.add(new Student());
        }
        Thread.sleep(100000000L);
    }
}
class Student {
    private byte[] big = new byte[1024 * 1024];
}
