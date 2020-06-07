package bean;

import java.io.Serializable;

/**
 * @ClassName Demo
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/22
 * @Version V1.0
 **/
public class DemoBean implements Serializable {
    int sn;
    public void setSn(int sn) {
        this.sn = sn;
    }

    public int getSn() {
        return sn;
    }
}
