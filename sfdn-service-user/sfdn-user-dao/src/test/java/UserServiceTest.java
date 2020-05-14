import dao.UserDao;
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
        UserDao userDao = (UserDao) context.getBean("userDao");
        System.out.println(userDao.getUser(1));
    }
}
