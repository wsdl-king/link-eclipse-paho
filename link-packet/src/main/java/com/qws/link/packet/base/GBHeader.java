package com.qws.link.packet.base;


import com.qws.link.ByteUtils;

import java.util.Arrays;

/**
 * 国标基础数据头
 */
public class GBHeader implements BaseHeader {
    /**
     * 一般已##开头
     */
    private String begin;
    /**
     * 命令标识
     */
    private int command;
    /**
     * 应答标识
     */
    private int answer;
    /**
     * 识别码(vin)
     */
    private String vin;
    /**
     * 加密类型
     */
    private int encryptType;
    /**
     * body数据长度
     */
    private int dataLength;


    public GBHeader(String begin, int command, int answer, String vin,
                    int encryptType, int dataLength) {
        super();
        this.begin = begin;
        this.command = command;
        this.answer = answer;
        this.vin = vin;
        this.encryptType = encryptType;
        this.dataLength = dataLength;
    }

    public GBHeader(byte[] bytes){
        build(bytes);
    }


    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public int getCommand() {
        return command;
    }

    public void setCommand(int command) {
        this.command = command;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(int encryptType) {
        this.encryptType = encryptType;
    }

    @Override
    public String toString() {
        return "FMHeader [begin=" + begin + ", command=" + command
                + ", answer=" + answer + ", vin=" + vin + ", encryptType="
                + encryptType + ", dataLength=" + dataLength + "]";
    }

    @Override
    public void build(byte[] bytes) {
        begin = new String(new byte[]{bytes[0], bytes[1]});
        command = 0xff & bytes[2];
        answer = 0xff & bytes[3];
        vin = new String(Arrays.copyOfRange(bytes, 4, 21));
        encryptType = 0xff & bytes[21];
        dataLength = ByteUtils.byteArrayToInt(new byte[]{0x00, 0x00, bytes[22], bytes[23]}, 0);
    }

    @Override
    public byte[] unbuild() {
        byte[] beginBytes = begin.getBytes();
        byte commonByte = ByteUtils.intToByteArray(command)[3];
        byte answerByte = ByteUtils.intToByteArray(answer)[3];
        byte[] vinBytes = vin.getBytes();
        if (vinBytes.length < 17) {
            vinBytes = Arrays.copyOf(vinBytes, 17);
        }
        byte encryptTypeByte = ByteUtils.intToByteArray(encryptType)[3];
        byte[] lengthBytes = ByteUtils.intToByteArray2(dataLength);
        byte[] result = new byte[beginBytes.length + vinBytes.length + 5];
        System.arraycopy(beginBytes, 0, result, 0, beginBytes.length);
        result[beginBytes.length] = commonByte;
        result[beginBytes.length + 1] = answerByte;
        System.arraycopy(vinBytes, 0, result, beginBytes.length + 2, vinBytes.length);
        result[result.length - 3] = encryptTypeByte;
        result[result.length - 2] = lengthBytes[0];
        result[result.length - 1] = lengthBytes[1];
        return result;
    }

    @Override
    public Integer length() {
        return null;
    }
}
