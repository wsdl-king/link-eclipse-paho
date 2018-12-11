package com.qws.link.packet.realtime;

import com.qws.link.ByteUtils;
import com.qws.link.tbox.common.PackageConstant;
import com.qws.link.tbox.common.base.BasePacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

/**
 * 车辆实时数据包
 */
public class RealTimePacket implements BasePacket, Serializable {
    private final Logger logger = LoggerFactory.getLogger(RealTimePacket.class);
    private static final long serialVersionUID = 7156899721601736162L;
    /**
     * 上报数据时间
     */
    private Date uploadTime;
    /**
     * 详情参考REALTIME_SUBINFO常量枚举类
     */
    private Map<PackageConstant.REALTIME_SUBINFO, BasePacket> subInfoPackets;

    private int length;

    @Override
    public void build(byte[] bytes) {
        try {
            uploadTime = ByteUtils.convertByteArrtDatetime(Arrays.copyOfRange(bytes, 0, 6));
            byte[] oddBytes = Arrays.copyOfRange(bytes, 6, bytes.length);
            subInfoPackets = bulidSubPacket(oddBytes);
        } catch (Exception e) {
            logger.error("", e);
        }

    }

    @Override
    public byte[] unbuild() {
        byte[] uploadTimeBytes = ByteUtils.convertDatetime2ByteArr(uploadTime);
        byte[] result = uploadTimeBytes;
        Map<PackageConstant.REALTIME_SUBINFO, BasePacket> sortMap = new TreeMap<PackageConstant.REALTIME_SUBINFO, BasePacket>(
                new MapKeyComparator());
        sortMap.putAll(subInfoPackets);
        for (Entry<PackageConstant.REALTIME_SUBINFO, BasePacket> entry : sortMap.entrySet()) {
            result = ByteUtils.addAll(result, entry.getKey().type(), entry.getValue().unbuild());
        }
        length = result.length;
        return result;
    }

    /**
     * 迭代获取实时信息下的各种数据包
     *
     * @param oddBytes
     * @return
     * @throws Exception
     */
    private static Map<PackageConstant.REALTIME_SUBINFO, BasePacket> bulidSubPacket(byte[] oddBytes) throws Exception {
        Map<PackageConstant.REALTIME_SUBINFO, BasePacket> subInfoPackets = new HashMap<>();
        while (oddBytes.length > 0) {
            int type = 0xff & oddBytes[0];
            PackageConstant.REALTIME_SUBINFO subInfoPacket = PackageConstant.getSubInfo(type);
            int length = 0;
            if (subInfoPacket != null) {
                length = subInfoPacket.build(Arrays.copyOfRange(oddBytes, 1, oddBytes.length), subInfoPackets);
                oddBytes = Arrays.copyOfRange(oddBytes, length + 1, oddBytes.length);
            } else {
                length = ByteUtils.byteArrayToInt(oddBytes[1], oddBytes[2]);
                length = 2 + length;
                oddBytes = new byte[0];
            }
//			oddBytes = Arrays.copyOfRange(oddBytes, length+1,oddBytes.length);
        }
        return subInfoPackets;
    }

    @Override
    public Integer length() {
        // TODO Auto-generated method stub
        return length;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Map<PackageConstant.REALTIME_SUBINFO, BasePacket> getSubInfoPackets() {
        return subInfoPackets;
    }

    public void setSubInfoPackets(Map<PackageConstant.REALTIME_SUBINFO, BasePacket> subInfoPackets) {
        this.subInfoPackets = subInfoPackets;
    }

    class MapKeyComparator implements Comparator<PackageConstant.REALTIME_SUBINFO> {

        @Override
        public int compare(PackageConstant.REALTIME_SUBINFO value1, PackageConstant.REALTIME_SUBINFO value2) {

            return (value1.type() - value2.type()) == 0 ? value1.type() : (value1.type() - value2.type());
        }
    }

}
