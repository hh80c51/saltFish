package com.fish.start;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @ClassName StartService
 * @Description: TODO
 * @Author hh
 * @Date 2020/5/20
 * @Version V1.0
 **/
public class StartService {
    public static void main(String[] args) throws Exception {

        long time = System.currentTimeMillis();
        start();
//        startByJetty();
        System.out.println("start ok. expenses " + (System.currentTimeMillis() - time));
    }

    /**
     *  @Description    : 发布服务，不启动durid监控
     *  @Method_Name    : start
     *  @throws Exception
     *  @return         : void
     *  @Creation Date  : 2018年1月18日 上午10:48:56
     *  @Author         : zhichaoding@hongkun.com zc.ding
     */
    public static void start() throws Exception{
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:spring/spring-config.xml");
        context.start();
        synchronized (Thread.currentThread()) {
            Thread.currentThread().wait();
        }

        context.close();
    }
}
