import dao.UserMapper;
import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName UserServiceTest
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class UserServiceTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("resource/spring-config.xml");
        UserMapper userDao = (UserMapper) context.getBean("userMapper");
        System.out.println(userDao.getUser(1));
    }
}
