package com.qws.link.base.header;

import com.qws.link.base.ByteArrayBuf;

/**
 * @author qiwenshuai
 * @note  所有类型的header
 * @since 18-12-14 16:20 by jdk 1.8
 */
public interface BaseHeader  {

    /**
     * 解析协议
     */
    public void build(ByteArrayBuf byteBuf) ;
    /**
     * 封装协议
     */
    public byte[] unbuild();
    /**
     * 消耗的长度
     */
    public Integer length();

    
    
}
