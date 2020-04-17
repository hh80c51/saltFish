package spring;

import bean.springbean.TestBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName AspectJTest
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/14
 * @Version V1.0
 **/
public class AspectJTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/aspectTest.xml");
        TestBean bean = (TestBean) bf.getBean("test");
        bean.test();
    }
}
