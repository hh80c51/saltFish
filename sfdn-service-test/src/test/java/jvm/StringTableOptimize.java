package jvm;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 常量池优化
 * 回车开始读取文件中的海量字符串
 * 然后使用jvisualvm进行内存监测
 */
public class StringTableOptimize {
    public static void main(String[] args) throws IOException {
        List<String> address = new ArrayList<>();
        System.in.read();
        for (int i = 0; i < 10; i++) {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("linux.words"),"utf-8"))){
                String line = null;
                long start = System.nanoTime();
                while (true){
                    line = reader.readLine();
                    if(line == null){
                        break;
                    }
                    //入池
//                    address.add(line);
                    address.add(line.intern());
                }
                System.out.println("cost:" + (System.nanoTime()-start)/1000000);
            }
        }
    }
}
