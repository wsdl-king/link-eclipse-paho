package com.qws.link.auto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 当做配置项自动启动
 **/
@Configuration
public class ServerAutoConfigure {

    @Bean(initMethod = "open", destroyMethod = "close")
    public InitServer initServer() {
        return new InitServer();
    }


}
