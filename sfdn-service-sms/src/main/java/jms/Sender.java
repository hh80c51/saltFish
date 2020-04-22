package jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName Sender
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/17
 * @Version V1.0
 **/
public class Sender {
    public static void main(String[] args) throws JMSException, InterruptedException {
        //连接消息服务器
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        Connection connection = connectionFactory.createConnection();
        //connection.start();

        Session session = connection.createSession(Boolean.TRUE,
                Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("my-queue");

        MessageProducer producer = session.createProducer(destination);
        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("大家好这是个测试");
            Thread.sleep(1000);
            //通过消息生产者发出消息
            producer.send(message);
        }
        session.commit();
        session.close();
        connection.close();
    }
}
