package com.fish.user.utils;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName ConnectionUtil
 * @Description: TODO
 * @Author hh
 * @Date 2020/4/22
 * @Version V1.0
 **/
public class ConnectionUtil {
    private static final String host = "localhost";
    private static final int port = 5672;
    private static final String username="test";
    private static final String password="123456";


    /**
     * 获取RabbitMQ Connection连接
     * @return
     * @throws IOException
     * @throws TimeoutException
     */
    public static Connection getConnection() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        return connectionFactory.newConnection();
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        getConnection();
    }
}
