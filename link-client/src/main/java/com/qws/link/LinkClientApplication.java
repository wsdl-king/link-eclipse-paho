package com.qws.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiwenshuai
 * @note  此程序充当生产者 向mqtt发布消息服务.
 * @since 18-12-10 14:42 by jdk 1.8
 */

@SpringBootApplication
public class LinkClientApplication {


    public static void main(String[] args) {

        SpringApplication.run(LinkClientApplication.class, args);
    }

}
