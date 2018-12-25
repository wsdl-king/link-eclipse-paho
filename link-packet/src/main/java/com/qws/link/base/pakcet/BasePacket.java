package com.qws.link.base.pakcet;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;

/**
 * @author qiwenshuai
 * @note 所有类型的packet
 * @since 18-12-14 16:46 by jdk 1.8
 */
public interface BasePacket  {

    /**
     * 解析协议
     */
    public abstract  void build(ByteArrayBuf buf) throws Exception ;
    /**
     * 封装协议
     */
    public  abstract byte[] unbuild();
    /**
     * 消耗的长度
     */
    public  abstract Integer length();



}
