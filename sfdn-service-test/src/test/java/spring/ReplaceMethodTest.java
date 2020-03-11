package spring;

import bean.springbean.TestChangeMethod;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReplaceMethodTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/replaceMethodTest.xml");
        TestChangeMethod  test = (TestChangeMethod) bf.getBean("testChangeMethod");
        test.changeMe();
    }
}
