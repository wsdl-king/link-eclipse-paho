package com.qws.link.realtime.children;

/**
 * 0x05
 * 实时信息上报-车辆位置数据
 */
public class VehicleLocationPacket {

    /** 定位状态 BYTE 0有效定位 1无效定位 */
    private Integer locationStatus;
    /** 纬度类型 0北纬 1南纬*/
    private Integer lonType;
    /** 经度类型 0东经 1西经*/
    private Integer latType;
    /** 经度 DWORD */
    private Double lon;
    /** 纬度 DWORD*/
    private Double lat;

    public Integer getLocationStatus() {
        return locationStatus;
    }

    public void setLocationStatus(Integer locationStatus) {
        this.locationStatus = locationStatus;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getLonType() {
        return lonType;
    }

    public void setLonType(Integer lonType) {
        this.lonType = lonType;
    }

    public Integer getLatType() {
        return latType;
    }

    public void setLatType(Integer latType) {
        this.latType = latType;
    }
}
