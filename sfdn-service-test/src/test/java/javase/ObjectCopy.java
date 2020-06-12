package javase;

import bean.DemoBean;
import org.apache.commons.lang3.SerializationUtils;

import java.io.Serializable;

public class ObjectCopy implements Cloneable, Serializable {
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
        shallowCopy();
        deepCopy();
    }

    private static void shallowCopy() throws CloneNotSupportedException {
        ObjectCopy objectCopy = new ObjectCopy();
        objectCopy.setA(1);
        DemoBean demoBean = new DemoBean();
        demoBean.setSn(1111111);
        objectCopy.setDemoBean(demoBean);
        //clone()属于浅拷贝，只拷贝对象的属性值，不复制属性对象引用地址
        ObjectCopy objectCopy1 = (ObjectCopy) objectCopy.clone();
        System.out.println("浅拷贝之后，内存地址是否相同？" + (objectCopy==objectCopy1));//false
        System.out.println("浅拷贝之后，新老对象的属性对象的内存地址是否相同？"+(objectCopy.getDemoBean() == objectCopy1.getDemoBean()));//true
        System.out.println("改变拷贝的对象属性是相同的，如果改变了其中一个的引用类型，另一个会不会也改变呢？");

        objectCopy1.getDemoBean().setSn(2222222);
        System.out.println(objectCopy1.getDemoBean().getSn());//2222222
        System.out.println(objectCopy.getDemoBean().getSn());//2222222
    }

    private static void deepCopy() throws CloneNotSupportedException {
        ObjectCopy objectCopy = new ObjectCopy();
        objectCopy.setA(1);
        DemoBean demoBean = new DemoBean();
        demoBean.setSn(1111111);
        objectCopy.setDemoBean(demoBean);
        //深拷贝指的是将引用类型的属性内容也拷贝一份新的
        ObjectCopy objectCopy2 = SerializationUtils.clone(objectCopy);
        System.out.println("深拷贝之后，内存地址是否相同？" + (objectCopy==objectCopy2));//false
        System.out.println("浅拷贝之后，新老对象的属性对象的内存地址是否相同？"+(objectCopy.getDemoBean() == objectCopy2.getDemoBean()));//false
        System.out.println("改变拷贝的对象属性是相同的，如果改变了其中一个的对象属性，另一个会不会也改变呢？");
        objectCopy2.getDemoBean().setSn(2222222);
        System.out.println(objectCopy2.getDemoBean().getSn());//2222222
        System.out.println(objectCopy.getDemoBean().getSn());//1111111
    }
}
