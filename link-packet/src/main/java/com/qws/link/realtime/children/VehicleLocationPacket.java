package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0x05
 * 实时信息上报-车辆位置数据
 */
public class VehicleLocationPacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(VehicleLocationPacket.class);
    private static final long serialVersionUID = 1189640763794794216L;

    /**
     * 定位状态 BYTE 0有效定位 1无效定位
     */
    private Integer locationStatus;
    /**
     * 纬度类型 0北纬 1南纬
     */
    private Integer lonType;
    /**
     * 经度类型 0东经 1西经
     */
    private Integer latType;
    /**
     * 经度 DWORD
     */
    private Double lon;
    /**
     * 纬度 DWORD
     */
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

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {

        int index = byteBuf.readerIndex();

        byte[] statusBytes = ByteUtils.byte2BitArray(byteBuf.readByte());
        this.locationStatus = ByteUtils.toInt(statusBytes[0]);
        this.lonType = ByteUtils.toInt(statusBytes[1]);
        this.latType = ByteUtils.toInt(statusBytes[2]);
        this.lon = ByteUtils.toDouble(byteBuf.readBytes(4), 1e6, 0);
        this.lat = ByteUtils.toDouble(byteBuf.readBytes(4), 1e6, 0);

        logger.info("[RealtimeDataPacket.VehicleLocationDataPacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {
        // 有效定位
        int effective = 0x00;
        // 纬度类型
        byte latTypeByte = ByteUtils.integerToByteAutoZero(this.getLatType());
        // 经度类型
        byte lonTypeByte = ByteUtils.integerToByteAutoZero(this.getLonType());
        // 经度
        byte[] lonBytes = ByteUtils.doubleToByteArrayFF(this.getLon(), 1000000, 0);
        // 纬度
        byte[] latBytes = ByteUtils.doubleToByteArrayFF(this.getLat(), 1000000, 0);

        // 定位状态
        byte locationStatusBytes = (byte) (effective + (latTypeByte << 1) + (lonTypeByte << 2));

        return ByteUtils.addAll(locationStatusBytes, lonBytes, latBytes);
    }

    @Override
    public Integer length() {
        return null;
    }

    public static void main(String[] args) {
    }
}
