import bean.User;
import dao.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import utils.MyBatisUtil;

/**
 * @ClassName TestMapper
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class TestMapper {
    static SqlSessionFactory sqlSessionFactory = null;
    static {
        sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
    }

    @Test
    public void testAdd() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = new User("tom", new Integer(5));
            userMapper.insertUser(user);
            sqlSession.commit();//这里一定要提交，不然数据进不去数据库
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.getUser(1);
            sqlSession.commit();//这里一定要提交，不然数据进不去数据库
        } finally {
            sqlSession.close();
        }
    }
}
