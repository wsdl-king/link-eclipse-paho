package com.qws.link.tbox.common.base;

/**
 * 国标基础内容包接口
 */
public interface BasePacket {
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
