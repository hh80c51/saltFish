package jmsListener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @ClassName MyMessageListener
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/20
 * @Version V1.0
 **/
public class MyMessageListener implements MessageListener {
    @Override
    public void onMessage(Message arg0) {
        TextMessage msg = (TextMessage) arg0;
        try {
            System.out.println(msg.getText());
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
