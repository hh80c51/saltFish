package springJms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.TextMessage;

/**
 * @ClassName HelloWorldReciver
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/20
 * @Version V1.0
 **/
public class HelloWorldReciver {
    public static void main(String[] args) throws JMSException {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext.xml"});
        JmsTemplate jmsTemplate = (JmsTemplate) context.getBean("jmsTemplate");
        Destination destination = (Destination) context.getBean("destination");

        TextMessage msg = (TextMessage) jmsTemplate.receive(destination);
        System.out.println("recive msg is:" + msg.getText());
    }
}
