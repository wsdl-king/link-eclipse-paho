package com.qws.link.packet.base.header;

/**
 * @author qiwenshuai
 * @note  所有类型的header
 * @since 18-12-14 16:20 by jdk 1.8
 */
public interface BaseHeader  {

    /**
     * 解析协议
     */
    public void build(byte[] bytes) ;
    /**
     * 封装协议
     */
    public byte[] unbuild();
    /**
     * 消耗的长度
     */
    public Integer length();

    
    
}
