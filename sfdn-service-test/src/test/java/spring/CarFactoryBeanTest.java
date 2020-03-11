package spring;

import bean.springbean.Car;
import bean.springbean.CarFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CarFactoryBeanTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring/carTest.xml");
        Car car = context.getBean("car", Car.class);
        CarFactoryBean carFactoryBean = context.getBean("&car", CarFactoryBean.class);
        System.out.println("[car]"+car.toString());
        System.out.println("[carFactoryBean]"+carFactoryBean.toString());
    }
}
