package spring;

import bean.springbean.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CustomTest {
    public static void main(String[] args) {
        ApplicationContext bf = new ClassPathXmlApplicationContext("spring/customTest.xml");
        User user = (User) bf.getBean("testBean");
        System.out.println(user.getUserName()+"，"+user.getEmail());
    }
}
