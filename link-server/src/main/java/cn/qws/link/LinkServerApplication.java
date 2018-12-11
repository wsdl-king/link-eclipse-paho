package cn.qws.link;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;

/**
 * @author qiwenshuai
 * @note
 * @since 18-12-10 14:42 by jdk 1.8
 */

@SpringBootApplication(exclude = {MongoAutoConfiguration.class,MongoDataAutoConfiguration.class})
public class LinkServerApplication {


    public static void main(String[] args) {

        SpringApplication.run(LinkServerApplication.class, args);
    }

}
