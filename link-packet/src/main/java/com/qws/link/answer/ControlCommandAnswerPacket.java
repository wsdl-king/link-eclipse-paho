package com.qws.link.answer;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.BasePacket;

import java.io.Serializable;

/**
 * 命令控制响应数据包
 */
public class ControlCommandAnswerPacket implements BasePacket, Serializable {

    private static final long serialVersionUID = 2061360311744372064L;
    private Long uploadTime;


    public Long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Long uploadTime) {
        this.uploadTime = uploadTime;
    }

    @Override
    public void build(ByteArrayBuf buf) throws Exception {
        uploadTime = ByteUtils.dateBytes2Long(buf.readBytes(6));
    }

    @Override
    public byte[] unbuild() {
        return new byte[0];
    }

    @Override
    public Integer length() {
        return null;
    }
}
