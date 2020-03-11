package bean.springbean;

import java.util.List;

public class MyTestBean {
    private String testStr = "testStr";
    private List arglist;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    public List getArglist() {
        return arglist;
    }

    public void setArglist(List arglist) {
        this.arglist = arglist;
    }
}
