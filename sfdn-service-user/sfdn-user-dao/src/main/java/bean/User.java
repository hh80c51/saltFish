package bean;

/**
 * @ClassName User
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/15
 * @Version V1.0
 **/
public class User {
    private Integer id;
    private String name;
    private Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    //必须要有这个无参构造方法，不然根据UserMappe.xml中的配置，在查询数据库时，将不能反射构造出User实例
    public User() {
        super();
    }
}
