package com.qws.link.logout;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.FMPacket;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note
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
}
