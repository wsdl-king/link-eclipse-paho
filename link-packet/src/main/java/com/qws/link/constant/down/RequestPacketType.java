package com.qws.link.constant.down;

/**
 * 下行命令发出枚举实体
 */
public enum RequestPacketType {

    /**
     * 查询命令
     */
    PARAM_QUERY(0x80),
    /**
     * 设置命令
     */
    PARAM_SETTING(0x81),
    /**
     * 车载终端控制命令
     */
    DEVICE_CONTROL(0x82);

    private int command;

    private RequestPacketType(int command) {
        this.command = command;
    }

    public int getCommand() {
        return command;
    }
}
