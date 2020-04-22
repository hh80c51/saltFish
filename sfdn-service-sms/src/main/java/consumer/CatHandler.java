package consumer;
import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
/**
 * @ClassName CatHandler
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/22
 * @Version V1.0
 **/


public class CatHandler implements MessageListener {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onMessage(Message msg) {

        //msg就是rabbitmq传来的消息
        // 使用jackson解析
//            JsonNode jsonData = MAPPER.readTree(msg.getBody());
//            System.out.println("我是可爱的小猪,我的id是" + jsonData.get("id").asText()
//                    + ",我的名字是" + jsonData.get("name").asText());
        System.out.println("Cat" + msg.toString());

    }

}
