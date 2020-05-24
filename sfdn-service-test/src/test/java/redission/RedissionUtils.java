package redission;

import org.redisson.Config;
import org.redisson.Redisson;
import org.redisson.RedissonClient;

public class RedissionUtils {

    private RedissionUtils() {
    }

    private static RedissionUtils redissionUtils;


    public static RedissionUtils getInstence() {
        if (redissionUtils == null) {
            synchronized (RedissionUtils.class) {
                if (redissionUtils == null) {
                    redissionUtils = new RedissionUtils();
                }
            }
        }
        return redissionUtils;
    }

    public RedissonClient getRedissionClient() {
        Config config = new Config();
        config.useSingleServer().setAddress("127.0.0.1:6379");
        RedissonClient redissionClient = Redisson.create(config);
        return redissionClient;
    }
}