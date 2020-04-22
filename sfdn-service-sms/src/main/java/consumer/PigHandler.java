package consumer;
import java.io.IOException;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @ClassName PigHandler
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/22
 * @Version V1.0
 **/

public class PigHandler implements MessageListener {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Override
    public void onMessage(Message msg) {
        //msg就是rabbitmq传来的消息，需要的同学自己打印看一眼
        // 使用jackson解析
//            JsonNode jsonData = MAPPER.readTree(msg.getBody());
/*            System.out.println("我是可爱的小猫,我的id是" + jsonData.get("id").asText()
                    + ",我的名字是" + jsonData.get("name").asText());*/
        System.out.println("Pig" + msg.toString());

    }

}

