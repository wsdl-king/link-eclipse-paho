package com.qws.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author qiwenshuai
 * @note    包含一个netty线程
 * @since 19-1-5 15:22 by jdk 1.8
 */
@SpringBootApplication
public class LinkNettyApplication {

    public static void main(String[] args) {
        SpringApplication.run(LinkNettyApplication.class, args);
    }
}
