package com.qws.link.base.header;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author qiwenshuai
 * @note FM 协议开头
 * @since 19-1-10 10:03 by jdk 1.8
 */
public class FMHeader implements BaseHeader, Serializable {

    private static final long serialVersionUID = -5037821311149783519L;
    public static final int HEADER_LENGTH = 24;

    public static final String HEADER_BEGIN = "!!";


    /**
     * 此处以!!开头,对应的hex为 0x21,0x21
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
     * 识别码(sn)
     */
    private String sn;
    /**
     * 加密类型 0x01 不加密,0x03 AES128
     */
    private int encryptType;
    /**
     * body数据长度
     */
    private int dataLength;

    public FMHeader(String begin, int command, int answer, String sn,
                    int encryptType, int dataLength) {
        super();
        this.begin = begin;
        this.command = command;
        this.answer = answer;
        this.sn = sn;
        this.encryptType = encryptType;
        this.dataLength = dataLength;
    }

    public FMHeader(ByteArrayBuf byteBuf) {
        build(byteBuf);
    }


    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
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
                + ", answer=" + answer + ", sn=" + sn + ", encryptType="
                + encryptType + ", dataLength=" + dataLength + "]";
    }

    @Override
    public void build(ByteArrayBuf byteBuf) {
        begin = new String(byteBuf.readBytes(2));
        command = ByteUtils.toInt(byteBuf.readByte());
        answer = byteBuf.readByte();
        sn = new String(byteBuf.readBytes(17)).trim();
        encryptType = ByteUtils.isCorrectData(byteBuf.readByte()) ? byteBuf.getByte(21) : -1;
        dataLength = ByteUtils.byteArrayToInt(new byte[]{0x00, 0x00, byteBuf.readByte(), byteBuf.readByte()}, 0);
    }

    @Override
    public byte[] unbuild() {
        byte[] beginBytes = begin.getBytes();
        byte commonByte = ByteUtils.intToByteArray(command)[3];
        byte answerByte = ByteUtils.intToByteArray(answer)[3];
        byte[] snBytes = sn.getBytes();
        if (snBytes.length < 17) {
            snBytes = Arrays.copyOf(snBytes, 17);
        }
        byte encryptTypeByte = ByteUtils.intToByteArray(encryptType)[3];
        byte[] lengthBytes = ByteUtils.intToByteArray2(dataLength);
        //24
        byte[] result = new byte[beginBytes.length + snBytes.length + 5];
        System.arraycopy(beginBytes, 0, result, 0, beginBytes.length);
        //2
        result[beginBytes.length] = commonByte;
        //3
        result[beginBytes.length + 1] = answerByte;
        //4,17
        System.arraycopy(snBytes, 0, result, beginBytes.length + 2, snBytes.length);
        //
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
