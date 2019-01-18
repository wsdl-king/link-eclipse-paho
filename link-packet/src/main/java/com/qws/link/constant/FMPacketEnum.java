package com.qws.link.constant;

import com.qws.link.answer.ControlCommandAnswerPacket;
import com.qws.link.base.pakcet.FMPacket;
import com.qws.link.check.FMCheckPacket;
import com.qws.link.login.FMRegPacket;
import com.qws.link.logout.FMLogoutPacket;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author qiwenshuai
 * @note FM packet专用枚举
 * @since 19-1-10 11:12 by jdk 1.8
 */
public enum FMPacketEnum {

    //车辆登入
    LOGIN(0x01, FMRegPacket.class),
    //实时信息上报
//  REALTIME(0x02, RealTimePacket.class),
    //补发信息上报
    //车辆登出
    LOGOUT(0x04, FMLogoutPacket.class),
    //平台数据传输
    //心跳
    //终端校时
    //上行数据系统预留
    //查询
    //设置
    //控制
    //控制应答
    CONTROLANSWER(0x82, ControlCommandAnswerPacket.class),
    //下行数据系统预留
    //平台交换自定义数据

    //下行升级校验
    CHECK(0xB2, FMCheckPacket.class);

    /**
     * 协议包命令
     */
    private int command;

    /**
     * 协议包对应的实体类名称
     */
    private Class<?> classType;

    FMPacketEnum(int command, Class<?> classType) {
        this.command = command;
        this.classType = classType;
    }

    public int getCommand() {
        return command;
    }

    public Class<?> getClassType() {
        return classType;
    }

    public static final Map<Integer, FMPacketEnum> responsePacketTypeMap = new HashMap<>();

    static {

        for (FMPacketEnum type : EnumSet.allOf(FMPacketEnum.class)) {
            responsePacketTypeMap.put(type.getCommand(), type);
        }
    }


    public static Map<Integer, FMPacketEnum> getResponsePacketTypeMap() {
        return responsePacketTypeMap;
    }


    public static FMPacketEnum getResponsePacketTypeByCommand(int command) {

        return responsePacketTypeMap.containsKey(command) ? responsePacketTypeMap.get(command) : null;
    }

    public FMPacket getResponsePacketInstance() throws IllegalAccessException, InstantiationException {

        return (FMPacket) this.classType.newInstance();
    }
}
