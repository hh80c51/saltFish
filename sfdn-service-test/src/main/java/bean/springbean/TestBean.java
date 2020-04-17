package bean.springbean;

import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName TestBean
 * @Description: 用于拦截的bean
 * @Author hh
 * @Date 2020/4/14
 * @Version V1.0
 **/
public class TestBean {
    private String testStr = "testStr";

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public void test(){
        System.out.println("test");
    }
}
