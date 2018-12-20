package com.qws.link.constant;

import com.qws.link.realtime.RegPacket;

/**
 * @author qiwenshuai
 * @note 枚举类, 需要协议双方确认
 * @since 18-12-17 14:28 by jdk 1.8
 */
public enum PacketEnum {
    //车辆登入
    LOGIN("1", RegPacket.class);
    //实时信息上报
    //补发信息上报
    //车辆登出
    //平台数据传输
    //心跳
    //终端校时
    //上行数据系统预留
    //查询
    //设置
    //控制
    //下行数据系统预留
    //平台交换自定义数据


    private String type;

    private Class<?> classType;

    PacketEnum(String type, Class<?> classType) {
        this.type = type;
        this.classType = classType;
    }

    public String getType() {
        return type;
    }

    public Class<?> getClassType() {
        return classType;
    }
}
