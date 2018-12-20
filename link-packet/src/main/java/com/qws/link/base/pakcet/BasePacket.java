package com.qws.link.base.pakcet;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;

/**
 * @author qiwenshuai
 * @note 所有类型的packet
 * @since 18-12-14 16:46 by jdk 1.8
 */
public abstract class BasePacket  {

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

    protected Integer toInt(byte b, int diff) {

        return ByteUtils.isCorrectData(b) ? ByteUtils.toInt(b, diff) : null;
    }

    protected Integer toInt(byte b) {

        return ByteUtils.isCorrectData(b) ? ByteUtils.toInt(b) : null;
    }

    protected Integer byteArrayToInt(byte[] bytes, int offset) {

        return ByteUtils.isCorrectData(bytes) ? ByteUtils.byteArrayToInt(bytes, offset) : null;
    }

    protected Double toDouble(byte[] bytes, double der, int diff) {
        return ByteUtils.isCorrectData(bytes) ? ByteUtils.toDouble(bytes, der, diff) : null;
    }

    protected Double toDoubleFirstMinus(byte[] bytes, double der, int diff) {
        return ByteUtils.isCorrectData(bytes) ? ByteUtils.toDouble(bytes, der, 0) - diff : null;
    }

    protected String getStringFromBytes(byte[] bytes) {

        return ByteUtils.isCorrectData(bytes) ? ByteUtils.getStringFromBytes(bytes) : null;
    }

}
