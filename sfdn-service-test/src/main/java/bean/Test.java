package bean;

public class Test implements Cloneable{
    private int a;
    private String b;
    private DemoBean demoBean;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public DemoBean getDemoBean() {
        return demoBean;
    }

    public void setDemoBean(DemoBean demoBean) {
        this.demoBean = demoBean;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Test test = new Test();
        test.setA(1);
        DemoBean demoBean = new DemoBean();
        demoBean.setSn(1111111);
        test.setDemoBean(demoBean);
        //clone()属于浅拷贝，只拷贝对象的属性值，不复制属性对象引用地址
        Test test1 = (Test) test.clone();

        System.out.println(test==test1);//false
        System.out.println(test.getA() == test1.getA());
        //深拷贝指的是将引用类型的属性内容也拷贝一份新的
        System.out.println(test.getDemoBean() == test1.getDemoBean());
        test1.setA(2);
        System.out.println(test.getA() == test1.getA());
    }
}
