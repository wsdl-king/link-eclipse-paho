package com.qws.link.constant;

import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.base.pakcet.GBPacket;
import com.qws.link.login.RegPacket;
import com.qws.link.logout.LogoutPacket;
import com.qws.link.realtime.base.RealTimePacket;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiwenshuai
 * @note 枚举类,  参照国标协议
 * @since 18-12-17 14:28 by jdk 1.8
 */
public enum PacketEnum {
    //车辆登入
    LOGIN(0x01, RegPacket.class),
    //实时信息上报
    REALTIME(0x02, RealTimePacket.class),
    //补发信息上报
    //车辆登出
    LOGOUT(0x04, LogoutPacket.class);
    //平台数据传输
    //心跳
    //终端校时
    //上行数据系统预留
    //查询
    //设置
    //控制
    //下行数据系统预留
    //平台交换自定义数据


    /**
     * 协议包命令
     */
    private int command;

    /**
     * 协议包对应的实体类名称
     */
    private Class<?> classType;

    PacketEnum(int command, Class<?> classType) {
        this.command = command;
        this.classType = classType;
    }

    public int getCommand() {
        return command;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public static final Map<Integer, PacketEnum> responsePacketTypeMap = new HashMap<>();

    static {

        for (PacketEnum type : EnumSet.allOf(PacketEnum.class)) {
            responsePacketTypeMap.put(type.getCommand(), type);
        }
    }


    public static Map<Integer, PacketEnum> getResponsePacketTypeMap() {
        return responsePacketTypeMap;
    }


    public static PacketEnum getResponsePacketTypeByCommand(int command) {

        return responsePacketTypeMap.containsKey(command) ? responsePacketTypeMap.get(command) : null;
    }

    public GBPacket getResponsePacketInstance() throws IllegalAccessException, InstantiationException {

        return (GBPacket) this.classType.newInstance();
    }
}
