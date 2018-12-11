package com.qws.link.tbox.common;

import com.qws.link.tbox.common.base.BasePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * 国标数据包常量
 */
public class PackageConstant {
    private PackageConstant() {
    }

    private static final Logger logger = LoggerFactory.getLogger(PackageConstant.class);
    /**
     * 会话状态缓存key
     */
    public static final String CACHE_KEY = "dispatcher";
    /**
     * 用来获取sessionid的key
     */
    public static final String CACHE_KEY_SESSION = "session";
    /**
     * 用来获取最后更新时间的key
     */
    public static final String CACHE_KEY_SERVER = "server";

    public static final String SESSION_CARID = "carId";
    public static final String SESSION_TYPE = "sessionType";

    public static final String SESSION_CODE = "code";
    /***/
    protected static final Map<Integer, UP_COMMAND> commandMap = new HashMap<>();
    /***/
    protected static final Map<Integer, REALTIME_SUBINFO> realtimeMap = new HashMap<>();
    /**
     * 注册
     */
    public static final int COMMAND_REG = 1;

    public static final int COMMAND_REAL_TIME = 2;

    /**
     * 下行  128-255
     */
    public static final int CONTROLLER_COMMAND = 0xff & 0x82;
    /**命令类型*/
    /**远程升级*/

    /**
     * 命令类型
     */
    public enum CONTROLLER_TYPE {
        /**
         * 更新
         */
        UPDATE(1),
        /**
         * 关闭
         */
        SHUTDOWN(2),
        /**
         * 复位
         */
        RESET(3),
        /**
         * 恢复出厂设置
         */
        FACTORY_RESET(4),
        /**
         * 断开连接
         */
        DISCON(5),
        /**
         * 告警
         */
        WARN(6),
        /**
         * 抽样
         */
        SAMPLE(7);

        private int type;

        private CONTROLLER_TYPE(int type) {
            this.type = type;
        }

        public int value() {
            return this.type;
        }
    }

    /**
     * 响应类型类型
     */
    public static final int ANSWER_SUCCESS = 1;

    public static final int ANSWER_ERROR = 2;

    public static final int ANSWER_REQUIRED = 254;

    /**
     * 下行 128-255
     */
    public enum DOWN_COMMAND {
        /**
         * 控制命令
         */
        CONTROLLER(0xff & 0x82),
        /**
         * 设置命令
         */
        SETUP(0xff & 0x81),
        /**
         * 查询命令
         */
        QUERY(0xff & 0x80);

        private int type;

        private DOWN_COMMAND(int type) {
            this.type = type;
        }

        public int value() {
            return this.type;
        }
    }


    /**
     * 上行  1-127
     */
    public enum UP_COMMAND {
        /**
         * 车辆登录
         */
        LOGIN(0xff & 0x01, "common.CarLoginPacket"),
        /**
         * 实时信息上报
         */
        REALTIME(0xff & 0x02, "realtime.RealTimePacket"),
        /**
         * 补发信息上报
         */
        AGAIN(0xff & 0x03, "realtime.RealTimePacket"),
        /**
         * 车辆登出
         */
        LOGIN_OUT(0xff & 0x04, "common.CarLoginOutPacket"),
        /**
         * 平台登录
         */
        PLT_LOGIN(0xff & 0x05, "common.PlatformLoginPacket"),
        /**
         * 平台登出
         */
        PLT_LOGIN_OUT(0xff & 0x06, "common.PlatformLoginOutPacket"),
        /**
         * 心跳
         */
        HEART(0xff & 0x07, ""),
        /**
         * 终端校时
         */
        TIME(0xff & 0x08, ""),
        /**
         * 控制命令-应答
         */
        CONTROLLER_ANSWER(0xff & 0x82, "CommandReturn"),
        /**
         * 控制命令-应答
         */
        CONTROLLER_ANSWER2(0xff & 0x84, "CommandReturn2"),
        /**
         * 设置命令—应答
         */
        SETUP_ANSWER(0xff & 0x81, ""),
        /**
         * 查询命令-应答
         */
        QUERY_ANSWER(0xff & 0x80, "QueryPacket"),
        /**
         * 查询命令-应答
         */
        QUERY_ANSWER2(0xff & 0x83, "QueryPacket2");

        private int type;
        private String beanName;

        private UP_COMMAND(int type, String beanName) {
            this.type = type;
            this.beanName = beanName;
        }

        public int type() {
            return this.type;
        }


    }

    /**
     * 实时上报数据子包类型
     */
    public enum REALTIME_SUBINFO {

        /**
         * 整车数据
         */
        CAR_INFO(0xff & 0x01, "CarInfoPacket"),
        /**
         * 驱动电机数据
         */
        DRIVE_MOTOR(0xff & 0x02, "DriveMotorPacket"),
        /**
         * 车辆位置
         */
        LOCATION(0xff & 0x05, "LocationPacket"),
        /**
         * 极值数据
         */
        EXTREME(0xff & 0x06, "ExtremumPacket"),
        /**
         * 报警数据
         */
        WARN(0xff & 0x07, "CarWarnPacket"),
        /**
         * 可充电储能装置电压数据
         */
        VOLTAGE(0xff & 0x08, "ChargeDeviceVoltagePacket"),
        /**
         * 可充电储能装置温度数据
         */
        TEMP(0xff & 0x09, "ChargeDeviceTempPacket"),
        /**
         * 江铃补充数据
         */
        CAR_EXPAND_MORE(0xff & 0x81, "CarExpandMorePacket");

        private int type;
        private String beanName;

        private REALTIME_SUBINFO(int type, String beanName) {
            this.type = type;
            this.beanName = beanName;
        }

        public int type() {
            return this.type;
        }

        /**
         * 反射调用静态方法获取数据实体,并放入map中
         */
        public Integer build(byte[] bytes, Map<REALTIME_SUBINFO, BasePacket> map) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
            Class<?> clazz = Class.forName("com.jmev.tbox.packet.realtime." + beanName);
            BasePacket packet = (BasePacket) clazz.newInstance();
            packet.build(bytes);
            map.put(this, packet);
            return packet.length();
        }
    }

    /**
     * 根据类型获取子包
     *
     * @param type
     * @return
     */
    public static REALTIME_SUBINFO getSubInfo(int type) {
        if (null != realtimeMap.get(type)) {
            return realtimeMap.get(type);
        }
        for (REALTIME_SUBINFO subInfo : REALTIME_SUBINFO.values()) {
            if (subInfo.type == type) {
                realtimeMap.put(type, subInfo);
                return subInfo;
            }
        }
        return null;
    }

    /**
     * 获取上行的数据包解析类
     *
     * @param type
     * @return
     */
    public static UP_COMMAND getUpPacket(int type) {
        if (null != commandMap.get(type)) {
            return commandMap.get(type);
        }
        for (UP_COMMAND upPacket : UP_COMMAND.values()) {
            if (upPacket.type == type) {
                commandMap.put(type, upPacket);
                return upPacket;
            }
        }
        return  null;
    }

}
