package java8;

import bean.springbean.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName TestLambda
 * @Description: TODO
 * @Author hh
 * @Date 2020/3/26
 * @Version V1.0
 **/
public class TestLambda {

    public static int BUSINESS_USER = 2;

    List<Employee> emps = Arrays.asList(
            new Employee(1,"张三", 18, 9999),
            new Employee(2,"李四", 59, 6666),
            new Employee(3,"王五", 28, 3333),
            new Employee(4,"赵六", 8, 7777),
            new Employee(5,"田七", 38, 5555)
    );

    @Test
    public void test1(){
//        Collections.sort(emps, (e1, e2) -> {
//            if(e1.getAge() == e2.getAge()){
//                return e1.getName().compareTo(e2.getName());
//            }else{
//                return Integer.compare(e1.getAge(), e2.getAge());
//            }
//        });
//
//        for (Employee emp : emps){
//            System.out.println(emp);
//        }
        boolean srt = String.valueOf(BUSINESS_USER).equals("2");
        System.out.println(srt);
    }
}
