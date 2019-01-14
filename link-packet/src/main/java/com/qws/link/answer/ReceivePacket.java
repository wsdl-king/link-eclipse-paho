package com.qws.link.answer;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.BasePacket;

import java.io.Serializable;

/**
 * @author qiwenshuai
 * @note 应答空包实现
 * @since 19-1-10 17:53 by jdk 1.8
 */
public class ReceivePacket implements BasePacket, Serializable {
    private static final long serialVersionUID = -6382312489570123041L;

    @Override
    public void build(ByteArrayBuf buf) throws Exception {

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
