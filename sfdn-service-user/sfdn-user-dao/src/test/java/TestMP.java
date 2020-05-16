import com.fish.user.dao.UserDao;
import com.fish.user.user.model.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName TestMP
 * @Description: MyBatis Plus测试
 * @Author hh
 * @Date 2020/5/15
 * @Version V1.0
 **/
public class TestMP {
    private ApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:resource/applicationContext.xml");

    private UserDao userDao =
            context.getBean("userDao", UserDao.class);
    @Test
    public void getEmpByIdTest() {
        User user = userDao.selectById(1);

        System.out.println(user);
    }

}
