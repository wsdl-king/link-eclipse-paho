package com.qws.link.codec;

/**
 * 生成/or检验 bbc校验码
 */
public class CheckCode {

    public static byte mathParity(byte[] headerBytes, byte[] bodyData){
        byte result = headerBytes[2];
        for(int i= 3; i <= headerBytes.length-1;i++){
            result ^= headerBytes[i];
        }
        for(int i= 0; i <= bodyData.length-1;i++){
            result ^= bodyData[i];
        }
        return result;
    }
}
