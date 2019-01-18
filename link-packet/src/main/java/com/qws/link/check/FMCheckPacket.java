package com.qws.link.check;
import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.FMPacket;
import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note 0xB2校验协议
 * @since 19-1-18 10:28 by jdk 1.8
 */
public class FMCheckPacket  implements FMPacket, Serializable {
    private static final long serialVersionUID = 4015791185946941815L;

    /**
     * 命令下发时间 6
     */
    private Long downTime;
    /**
     * 校验数据 1
     * 0x01 可以升级
     * 0x02 无效设备
     * 0x03 升级协议未知
     * 0x04 无升级任务
     * 0x05 无需升级(>=版本号)
     * 0x06 异常原因不可升级
     * */
    private Integer checkValue;

    /**
     * 附加数据长度 1
     */
    private Integer attachLength;

    /**
     * 附加数据 x
     */
    private String attachValue;


    public Long getDownTime() {
        return downTime;
    }

    public void setDownTime(Long downTime) {
        this.downTime = downTime;
    }

    public Integer getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(Integer checkValue) {
        this.checkValue = checkValue;
    }

    public Integer getAttachLength() {
        return attachLength;
    }

    public void setAttachLength(Integer attachLength) {
        this.attachLength = attachLength;
    }

    public String getAttachValue() {
        return attachValue;
    }

    public void setAttachValue(String attachValue) {
        this.attachValue = attachValue;
    }

    @Override
    public void build(ByteArrayBuf buf) throws Exception {
        downTime = ByteUtils.dateBytes2Long(buf.readBytes(6));
        checkValue = ByteUtils.toInt(buf.readByte());
        attachLength = ByteUtils.toInt(buf.readByte());
        attachValue = ByteUtils.getStringFromBytes(buf.readBytes(attachLength));
    }

    @Override
    public byte[] unbuild() {
        //6
        byte[] downTimeBytes = ByteUtils.longTimeToByteArray(this.downTime);
        //  校验值 1字节
        byte[] checkBytes = ByteUtils.intToByteArray(checkValue, 1);
        //附加数据长度 1
        byte[] attLengthBytes = ByteUtils.intToByteArray(attachLength, 1);
        //附加数据 x
        byte[] attValueBytes = ByteUtils.string2Bytes(attachValue);
        return ByteUtils.addAll(downTimeBytes, checkBytes, attLengthBytes, attValueBytes);
    }

    @Override
    public Integer length() {
        return null;
    }

    public static void main(String[] args) {

        FMCheckPacket fmCheckPacket = new FMCheckPacket();
        fmCheckPacket.setDownTime(20181115114338L);
        fmCheckPacket.setAttachLength(0);
        fmCheckPacket.setCheckValue(1);
        fmCheckPacket.setAttachValue("");
        System.out.println(JSON.toJSONString(fmCheckPacket));
    }
}
