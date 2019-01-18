package com.qws.link.logout;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.FMHeader;
import com.qws.link.base.pakcet.FMPacket;
import com.qws.link.codec.CheckCode;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note FM登出包
 * @since 19-1-10 14:48 by jdk 1.8
 */
public class FMLogoutPacket implements FMPacket, Serializable {


    private static final long serialVersionUID = 4927025691701709449L;

    /**
     * 时间
     */
    private Long uploadTime;
    /**
     * 登出流水号
     */
    private Integer logoutSnArg;

    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getLogoutSnArg() {
        return logoutSnArg;
    }

    public void setLogoutSnArg(Integer logoutSnArg) {
        this.logoutSnArg = logoutSnArg;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(byteBuf.readBytes(6));

        logoutSnArg = ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2);
    }

    @Override
    public byte[] unbuild() {
        // 上报时间
        byte[] uploadTimeBytes = ByteUtils.longTimeToByteArray(this.uploadTime);
        // 登出流水号
        byte[] logoutSnArgBytes = ByteUtils.intToByteArray(this.logoutSnArg, 2);

        return ByteUtils.addAll(uploadTimeBytes, logoutSnArgBytes);
    }

    @Override
    public Integer length() {
        return null;
    }


    /**
     * 登出报文示例
     */
    public static void main(String[] args) {
        FMHeader fmHeader = new FMHeader("!!", 4, 254, "FM-12345678901234", 1, 8);
        byte[] header = fmHeader.unbuild();
        String headerString = ByteUtils.asHex(header);
        FMLogoutPacket fmLogoutPacket = new FMLogoutPacket();
        fmLogoutPacket.setLogoutSnArg(65530);
        fmLogoutPacket.setUploadTime(20181115115038L);
        byte[] packet = fmLogoutPacket.unbuild();
        String paString = ByteUtils.asHex(packet);
        byte[] bytes = new byte[1];
        bytes[0]=CheckCode.mathParity(header,packet);
        String bcc = ByteUtils.asHex(bytes);
        System.out.println(headerString);
        System.out.println(paString);
        System.out.println(bcc);
    }
}
