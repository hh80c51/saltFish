package web;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @ClassName RabbitmqController
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/22
 * @Version V1.0
 **/

@Controller
@RequestMapping(value="rabbit")
public class RabbitmqController<V> {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public void test() {

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("name", "pig");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_pig_key", map);

        map.put("id", "2");
        map.put("name", "cat");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_cat_key", map);

    }

    public static void main(String[] args) {
        ApplicationContext sendContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-rabbitmq-send.xml"});
        ApplicationContext receiveContext = new ClassPathXmlApplicationContext(new String[]{"spring/applicationContext-rabbitmq-recep.xml"});
        RabbitTemplate rabbitTemplate = sendContext.getBean(RabbitTemplate.class);
        RabbitTemplate rabbitTemplate1 = receiveContext.getBean(RabbitTemplate.class);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("id", "1");
        map.put("name", "pig");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_pig_key", "routeKey1");
//        rabbitTemplate1.convertAndSend("que_pig_key", 111);


        map.put("id", "2");
        map.put("name", "cat");
        //根据key发送到对应的队列
        rabbitTemplate.convertAndSend("que_cat_key", "routeKey2");
//        rabbitTemplate1.convertAndSend("que_cat_key", 222);

    }
}