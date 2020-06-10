package jvm;

public class StringTableQuestion {
    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "a" + "b";  //编译器优化为"ab"，入池
        String s4 = s1 + s2;    //new String("ab")
        String s5 = "ab";
        String s6 = s4.intern();

        System.out.println(s3 == s4);
        System.out.println(s3 == s5);
        System.out.println(s3 == s6);

        String x2 = new String("a") + new String("b");  //new String("cd")
        String x1 = "cd";
        x2.intern();

        //问，如果调换了最后两行的位置呢，如果是jdk1.6呢？
        System.out.println(x1 == x2);
    }
}
