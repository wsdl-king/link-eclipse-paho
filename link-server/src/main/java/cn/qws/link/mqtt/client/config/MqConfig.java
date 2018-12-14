package cn.qws.link.mqtt.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author qiwenshuai
 * @note mqtt client 我作为客户端需要用此客户端去订阅及发送
 * @since 18-12-13 18:05 by jdk 1.8
 */
@Configuration
public class MqConfig {

    @Value("${mqtt.username}")
    private String userName;

    @Value("${mqtt.password}")
    private String password;

    @Value("${mqtt.host}")
    private String host;

    @Value("${mqtt.clientId}")
    private String clientId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }


}
