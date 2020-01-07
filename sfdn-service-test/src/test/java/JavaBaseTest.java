import org.junit.Test;

public class JavaBaseTest {

    @Test
    public void test1() {
        String s = "AABB";
        String s2 = "AA"+"BB";
        System.out.println(s==s2);
        String k1 = "AA";
        String k2 = "BB";
        String k = k1 + k2;
        System.out.println(s==k);
        final String t1 = "AA";
        final String t2 = "BB";
        final String t = t1 + t2;
        System.out.println(s==t);
    }

}
