package com.qws.link.packet.realtime;


import com.qws.link.ByteUtils;
import com.qws.link.tbox.common.base.BasePacket;

import java.io.Serializable;

/**
 * 位置数据包
 */
public class LocationPacket implements BasePacket,Serializable{
	private static final long serialVersionUID = -3594577639324328409L;
	/**0 有效定位 1无效定位*/
	private Integer status;
	/**0 东经 1西经*/
	private Integer lonType;
	/**0 北纬 1南纬*/
	private Integer latType;
	/**经度*/
	private Double lon;
	/**纬度*/
	private Double lat;

	@Override
	public void build(byte[] bytes) {
		byte[] statusBytes= ByteUtils.byte2BitArray(bytes[0]);
		status = bytes[0]==0?0:1;
		latType = 0xff & statusBytes[1];
		lonType = 0xff & statusBytes[2];
		lon = ByteUtils.toDouble(bytes[1],bytes[2],bytes[3],bytes[4],1000000, 0);
		lat = ByteUtils.toDouble(bytes[5],bytes[6],bytes[7],bytes[8], 1000000, 0);
		//是否需要处理经纬度的转换？TODO
//		double[] lonlat = LocationUtils.transformFromWGSToGCJ(lat, lon);
//		lat = lonlat[0];
//		lon = lonlat[1];
	}

	@Override
	public byte[] unbuild()  {
		Integer statusInt = 0;
		statusInt+=status;
		statusInt+=latType*2;
		statusInt+=lonType*2*2;
		byte statusByte = statusInt.byteValue();
//		double[] lonlat = LocationUtils.gcj02towgs84(lat, lon);
//		lat = lonlat[0];
//		lon = lonlat[1];
		byte[] lonBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(lon,1000000,0), 4);
		byte[] latBytes = ByteUtils.intToByteArray(ByteUtils.DoubleToInt(lat,1000000,0), 4);
		return ByteUtils.addAll(statusByte,lonBytes,latBytes);
	}
	@Override
	public Integer length() {
		// TODO Auto-generated method stub
		return 9;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
