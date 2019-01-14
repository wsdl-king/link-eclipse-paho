package com.qws.link.entity;


/**
 * 空中升级包--对应的请求实体
 */
public class ReqRemoteUpgrade {


    /**
     * 设备信息请求头
     */
    private ReqHeader[] reqHeaders;
    /**
     * 拨号点名称
     */
    private String dialPoint;
    /**
     * 拨号用户名
     */
    private String dialUser;
    /**
     * 拨号密码
     */
    private String dialPwd;
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 生产厂商代码
     */
    private String manufacturer;
    /**
     * 硬件版本
     */
    private String hardwareVersion;
    /**
     * 固件版本
     */
    private String firmwareVersion;
    /**
     * 连接到升级服务器时限
     */
    private Integer timeLimit;
    /**
     * URL地址
     */
    private String url;

    public String getDialPoint() {
        return dialPoint;
    }

    public void setDialPoint(String dialPoint) {
        this.dialPoint = dialPoint;
    }

    public String getDialUser() {
        return dialUser;
    }

    public void setDialUser(String dialUser) {
        this.dialUser = dialUser;
    }

    public String getDialPwd() {
        return dialPwd;
    }

    public void setDialPwd(String dialPwd) {
        this.dialPwd = dialPwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public ReqHeader[] getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(ReqHeader[] reqHeaders) {
        this.reqHeaders = reqHeaders;
    }
}
