package serializable;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HessianSerializable
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/22
 * @Version V1.0
 **/
public class HessianSerializable {
    public static void main(String[] args) throws IOException {
        long start = System.currentTimeMillis();
        setSerializableObject();
        System.out.println("Hessian 序列化时间:" + (System.currentTimeMillis() - start) + " ms" );
        start = System.currentTimeMillis();
        getSerializableObject();
        System.out.println("Hessian 反序列化时间:" + (System.currentTimeMillis() - start) + " ms");
    }
    public static void setSerializableObject() throws IOException {
        Output output = new Output(new FileOutputStream("D:/file1.bin"));
        HessianOutput hessianOutput = new HessianOutput(output);
        for (int i = 0; i < 100000; i++) {
            Map<String,Integer> map = new HashMap<String, Integer>(2);
            map.put("zhang0", i);
            map.put("zhang1", i);
            hessianOutput.writeObject(new Simple("zhang"+i,(i+1),map));
        }
        output.flush();
        output.close();
    }
    public static void getSerializableObject() throws IOException {
        FileInputStream fi = null;
        ObjectInputStream si = null;
        HessianInput hessianInput = null;
        byte[] simpleArray = null;
        try {
            fi = new FileInputStream("D:/file2.bin");
            si = new ObjectInputStream(fi);

            // Hessian的反序列化读取对象
            hessianInput = new HessianInput(si);
            hessianInput.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            hessianInput.close();
            si.close();
            fi.close();
        }
    }
}
