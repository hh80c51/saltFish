package jvm.stringTable;

/**
 * 串池
 */
//StringTable [ "a", "b", "ab" ]    hashtable结构，不能扩容
public class StringTableInternDemo {
    public static void main(String[] args) {
//        stringTable();
        intern();
    }

    private static void stringTable(){
        //常量池中的信息，都会被加载到运行时常量池中，这时a b ab都是常量池中的符号，还没有变为java中的字符串对象
        //ldc #2 会把a符号变为"a"字符串对象
        //ldc #3 会把b符号变为"b"字符串对象
        //ldc #4 会把ab符号变为"ab"字符串对象
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        String s4 = s1 + s2;    //new StringBuilder().append("a").append("b").toString()
        String s5 = "a" + "b";  //javac 在编译期间的优化，结果已经在编译期间确定为ab

        System.out.println(s3 == s4);   //s3在串池中，toString中new了一个String对象，s4在堆中，地址不同，所以不相等
    }

    private static void intern(){
        //["ab", "a", "b"]
        String x = "ab";

        //堆 new String("a") new String("b") new String("ab")
        String s = new String("a") + new String("b");

        //将这个字符串对象尝试放入串池，如果有则并不会放入，如果没有则放入串池，会把串池中的对象返回
        String si = s.intern();

        //如果在s之前串池中没有ab，new String("ab")之后，"ab"也就是s放入串池，并且返回"ab"，si引用的对象就是串池中的ab
        //如果在s之前串池中有ab，si就是串池中的"ab",而s是new String("ab")
        System.out.println(si == "ab");
        System.out.println(s == "ab");
    }
}
