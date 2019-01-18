package com.qws.link.entity;


/**
 * 设备信息发送头
 */
public class ReqHeader {


    /**
     * vin
     */
    private String vin;
    /**
     * sn
     */
    private String sn;


    public ReqHeader() {
    }

    public ReqHeader(String vin) {
        this.vin = vin;
    }


    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

}
