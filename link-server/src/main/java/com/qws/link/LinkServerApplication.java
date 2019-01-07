package com.qws.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.kafka.annotation.EnableKafka;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-10 14:42 by jdk 1.8
 */
//没用用到mongodb 先不去自动加载了
@SpringBootApplication()
@EnableKafka
public class LinkServerApplication {


    public static void main(String[] args) {

        SpringApplication.run(LinkServerApplication.class, args);
    }

}
