package com.qws.link.login;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.FMHeader;
import com.qws.link.base.pakcet.FMPacket;
import com.qws.link.codec.CheckCode;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author qiwenshuai
 * @note FM登入协议
 * @since 19-1-10 11:13 by jdk 1.8
 */
public class FMRegPacket implements FMPacket, Serializable {


    private static final long serialVersionUID = -3482417664559338642L;
    /**
     * 数据采集时间 6
     */
    private Long uploadTime;
    /**
     * 登入流水号 2
     */
    private Integer loginSnArg;
    /**
     * SIM卡ICCID号 20
     */
    private String iccid;
    /**
     * vin 无效填写全0xff
     */
    private String vin;

    /**
     * 硬件版本长度 1
     */
    private Integer fLength;
    /**
     * 硬件版本 n
     */
    private String fValue;

    /**
     * 软件版本长度 1
     */
    private Integer sLength;
    /**
     * 软件版本 m
     */
    private String sValue;


    /**
     * 协议标识 1 (1,2,3,----v1,v2,v3)
     * */
    private Integer proFlag;

    /**
     * sn 17
     * */
    private String sn;


    /**
     * 车载终端制造商ID 4字节
     * */
    private String manufacturer;


    /**
     * 终端运行状态 1 0-正常 1-故障
     */
    private Integer runStatus;

    /**
     * 附加数据长度 1
     */
    private Integer attachLength;

    /**
     * 附加数据 x
     */
    private String attachValue;


    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getLoginSnArg() {
        return loginSnArg;
    }

    public void setLoginSnArg(Integer loginSnArg) {
        this.loginSnArg = loginSnArg;
    }

    public String getIccid() {
        return iccid;
    }

    public void setIccid(String iccid) {
        this.iccid = iccid;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public Integer getfLength() {
        return fLength;
    }

    public void setfLength(Integer fLength) {
        this.fLength = fLength;
    }

    public String getfValue() {
        return fValue;
    }

    public void setfValue(String fValue) {
        this.fValue = fValue;
    }

    public Integer getsLength() {
        return sLength;
    }

    public void setsLength(Integer sLength) {
        this.sLength = sLength;
    }

    public String getsValue() {
        return sValue;
    }

    public void setsValue(String sValue) {
        this.sValue = sValue;
    }

    public Integer getRunStatus() {
        return runStatus;
    }

    public void setRunStatus(Integer runStatus) {
        this.runStatus = runStatus;
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

    public Integer getProFlag() {
        return proFlag;
    }

    public void setProFlag(Integer proFlag) {
        this.proFlag = proFlag;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Override
    public void build(ByteArrayBuf buf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(buf.readBytes(6));
        loginSnArg = ByteUtils.byteArrayToInt(buf.readBytes(2), 2);
        iccid = ByteUtils.getStringFromBytes(buf.readBytes(20));
        vin = ByteUtils.getStringFromBytes(buf.readBytes(17));
        fLength = ByteUtils.toInt(buf.readByte());
        fValue = ByteUtils.getStringFromBytes(buf.readBytes(fLength));
        sLength = ByteUtils.toInt(buf.readByte());
        sValue = ByteUtils.getStringFromBytes(buf.readBytes(sLength));
        proFlag = ByteUtils.toInt(buf.readByte());
        sn = ByteUtils.getStringFromBytes(buf.readBytes(17));
        manufacturer = ByteUtils.getStringFromBytes(buf.readBytes(20));
        runStatus = ByteUtils.toInt(buf.readByte());
        attachLength = ByteUtils.toInt(buf.readByte());
        attachValue = ByteUtils.getStringFromBytes(buf.readBytes(attachLength));
    }

    @Override
    public byte[] unbuild() {
        byte[] uploadTimeBytes = ByteUtils.longTimeToByteArray(this.uploadTime);
        // 登入流水号
        byte[] loginSnArgBytes = ByteUtils.intToByteArray(this.loginSnArg, 2);
        // iccid
        byte[] iccidBytes = this.iccid.getBytes();
        //目前参考以0补齐, 协议是无效全为0xff
        byte[] vinBytes = this.vin.getBytes();
        if (vinBytes.length < 17) {
            vinBytes = Arrays.copyOf(vinBytes, 17);
        }
        //硬件长度 1字节
        byte[] fLengthBytes = ByteUtils.intToByteArray(fLength, 1);
        //硬件版本
        byte[] fValueBytes = ByteUtils.string2Bytes(fValue);
        //软件长度 1字节
        byte[] sLengthBytes = ByteUtils.intToByteArray(sLength, 1);
        //软件版本
        byte[] sValueBytes = ByteUtils.string2Bytes(sValue);
        //协议标识
        byte[] proBytes = ByteUtils.intToByteArray(proFlag, 1);
        //sn
        byte[] snBytes = this.sn.getBytes();
        if (snBytes.length < 17) {
            snBytes = Arrays.copyOf(snBytes, 17);
        }
        //车载终端制造商ID
        byte[] maBytes = this.manufacturer.getBytes();
        //终端运行状态
        byte[] runBytes = ByteUtils.intToByteArray(runStatus, 1);
        //附加数据长度
        byte[] attLengthBytes = ByteUtils.intToByteArray(attachLength, 1);
        //附加数据
        byte[] attValueBytes = ByteUtils.string2Bytes(attachValue);
        return ByteUtils.addAll(uploadTimeBytes, loginSnArgBytes, iccidBytes, vinBytes, fLengthBytes, fValueBytes, sLengthBytes, sValueBytes,proBytes,snBytes,maBytes, runBytes, attLengthBytes, attValueBytes);
    }

    @Override
    public Integer length() {
        return null;
    }


    /**
     * 登录报文示例
     */
    public static void main(String[] args) {
        FMHeader fmHeader = new FMHeader("!!", 1, 254, "FM-12345678901234", 1, 58);
        FMRegPacket fmRegPacket = new FMRegPacket();
        fmRegPacket.setUploadTime(20181115114338L);
        fmRegPacket.setLoginSnArg(65530);
        fmRegPacket.setIccid("12345678901234567890");
        fmRegPacket.setVin("fm-12345678901234");
        fmRegPacket.setfLength(4);
        fmRegPacket.setfValue("qwer");
        fmRegPacket.setsLength(3);
        fmRegPacket.setsValue("abc");
        fmRegPacket.setProFlag(1);
        fmRegPacket.setSn("fm012345678901234");
        fmRegPacket.setManufacturer("FMFM");
        fmRegPacket.setRunStatus(1);
        fmRegPacket.setAttachLength(2);
        fmRegPacket.setAttachValue("hh");
        byte[] packetBytes = fmRegPacket.unbuild();
        String p = ByteUtils.asHex(packetBytes);

        byte[] headerBytes = fmHeader.unbuild();
        byte bcc = CheckCode.mathParity(headerBytes, packetBytes);
        String h = ByteUtils.asHex(headerBytes);
        byte[] bytes = new byte[1];
        bytes[0] = bcc;
        byte[] bytes1 = ByteUtils.addAll(headerBytes, packetBytes, bcc);
        String b = ByteUtils.asHex(bytes);
        System.out.println(h);
        System.out.println(p);
        System.out.println(b);

        System.out.println(ByteUtils.asHex(bytes1));


        System.out.println(JSON.toJSONString(fmRegPacket));
    }
}
