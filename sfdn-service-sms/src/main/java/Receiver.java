import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName Receiver
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class Receiver {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        connection.start();

        final Session session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");

        MessageConsumer consumer = session.createConsumer(destination);
        for (int i = 0; i < 3; i++) {
            TextMessage message = (TextMessage) consumer.receive();
            session.commit();
            System.out.println("收到消息："+ message.getText());
        }
        session.close();
        connection.close();
    }
}
