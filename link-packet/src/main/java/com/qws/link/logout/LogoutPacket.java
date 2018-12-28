package com.qws.link.logout;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;

import java.io.Serializable;

/**
 * 车辆登出数据包
 */
public class LogoutPacket implements GBPacket, Serializable {

    private static final long serialVersionUID = -836128709201307720L;
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
        int index = 0;
        uploadTime = ByteUtils.dateBytes2Long(byteBuf.readBytes(6));

        logoutSnArg = ByteUtils.byteArrayToInt(byteBuf.readBytes(2), 2);
    }

    @Override
    public byte[] unbuild() {
        // 上报时间
        byte[] uploadTime = ByteUtils.longTimeToByteArray(this.uploadTime);
        // 登出流水号
        byte[] logoutSnArg = ByteUtils.intToByteArray(this.logoutSnArg, 2);

        return ByteUtils.addAll(uploadTime, logoutSnArg);
    }

    @Override
    public Integer length() {
        return null;
    }
}
