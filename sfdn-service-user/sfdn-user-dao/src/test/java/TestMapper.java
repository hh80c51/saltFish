import dao.UserDao;
import model.User;
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
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            User user = new User(new Integer(5), "tom");
            userDao.insertUser(user);
            sqlSession.commit();//这里一定要提交，不然数据进不去数据库
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void getUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            userDao.getUser(1);
            sqlSession.commit();//这里一定要提交，不然数据进不去数据库
        } finally {
            sqlSession.close();
        }
    }
}
