package com.qws.link.entity;

/**
 * @author qiwenshuai
 * @note 放一些channel的其他属性
 * @since 19-1-7 16:59 by jdk 1.8
 */
public class ChannelAttr {

    /**
     * 设备sn
     */
    private String sn;

    /**
     * 车辆id
     */
    private String vin;

    /**
     * iccid
     */
    private String iccid;
    /**
     * 设备id
     */
    private String deviceId;

    /**
     * 硬件版本
     */
    private String hvsn;

    /**
     * 软件版本
     */
    private String svsn;


    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getHvsn() {
        return hvsn;
    }

    public void setHvsn(String hvsn) {
        this.hvsn = hvsn;
    }

    public String getSvsn() {
        return svsn;
    }

    public void setSvsn(String svsn) {
        this.svsn = svsn;
    }

}
