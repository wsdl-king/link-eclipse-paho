package com.qws.link.prototype;

import com.qws.link.constant.PacketEnum;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiwenshuai
 * @note 会被标注为一个Map 实体 存储协议间对接好的数据
 * @since 18-12-17 11:45 by jdk 1.8
 */
@Component
public class GBStrategy {

    private static final ConcurrentHashMap<String, PacketEnum> map = new ConcurrentHashMap<>();

    static {
        //   这个应该考虑配置文件注入
        map.put("1", PacketEnum.LOGIN);
    }

    private GBStrategy() {
    }

}
