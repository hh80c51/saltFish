package utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @ClassName MyBatisUtil
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class MyBatisUtil {
    private final static SqlSessionFactory sqlSessionFactory;
    static {
        String resource = "resource/mybatis-config.xml";
        Reader reader = null;
        try {
            reader = Resources.getResourceAsReader(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
