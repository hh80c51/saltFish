import dao.UserDao;
import com.fish.user.model.User;
import org.easymock.EasyMock;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName EasyMockTest
 * @Description: Mock测试
 * @Author hh
 * @Date 2020/5/9
 * @Version V1.0
 **/
public class EasyMockTest {

    private ApplicationContext context =
            new ClassPathXmlApplicationContext("classpath:resource/applicationContext.xml");

    private UserDao userDao =
            context.getBean("userDao", UserDao.class);

    @Test
    public void testGet(){
        User expectedUser = new User();
        expectedUser.setId(1);
        UserDao mock = EasyMock.createMock(UserDao.class);  //创建Mock对象
        //录制Mock对象逾期行为
        EasyMock.expect(mock.selectById(1)).andReturn(expectedUser);
        /**
         //这样无论传入的id是多少，都会返回这个expectedUser而不是发生Unexpect method的Mock异常
         EasyMock.expect(mock.getUser(EasyMock.isA(Integer.class))).andReturn(expectedUser);
         //调用getUser方法时发生异常
         EasyMock.expect(mock.getUser(1)).andThrow(new RuntimeException());
         //void方法Mock
         mock.insertUser(expectedUser);
         EasyMock.expectLastCall().anyTimes();

        **/
        EasyMock.replay(mock);
        //重放Mock对象，测试时以录制的对象预期行为代替真实对象的行为
//        userService.setUserDao(mock);
        //调用测试方法
//        User user = userService.getUserById(1);
        //断言测试结果
//        assertEquals(expectedUser, user);
        //验证Mock对象被调用
        EasyMock.verify(mock);
    }

}
