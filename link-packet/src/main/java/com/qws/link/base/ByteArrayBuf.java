package com.qws.link.base;


/**
 * @author zhangsi
 * @date created in 2018/6/27 16:05
 */
public class ByteArrayBuf {

    private byte[] bytes;

    private int readerIndex;

    private int markedReaderIndex;

    private ByteArrayBuf(byte[] bytes) {
        this.bytes = bytes;
        this.readerIndex = 0;
        this.markedReaderIndex = 0;
    }


    /**
     * 对于数据传递过来的字节,进行了包装,使其变成包装类,提供更多样化的方法.
     * */
    public static ByteArrayBuf  warp(byte[] bytes) {
        if (bytes == null) return new ByteArrayBuf(new byte[0]);
        return new ByteArrayBuf(bytes);
    }

    public byte[] readBytes(int i) {
        if (i < 0) throw new IllegalArgumentException("i must be greater than 0. " + "i:" + i);
        checkReadableBytes(i);
        byte[] copy = new byte[i];
        int length = Math.min(bytes.length - readerIndex, i);
        System.arraycopy(bytes, readerIndex, copy, 0, length);
        readerIndex = readerIndex + length;
        return copy;
    }

    public void readBytes(byte[] bytes) {

        if (bytes == null) throw new NullPointerException("bytes must not be null");

        checkReadableBytes(bytes.length);

        int length = Math.min(this.bytes.length - readerIndex, bytes.length);

        System.arraycopy(this.bytes, readerIndex, bytes, 0, length);

        readerIndex = readerIndex + length;
    }

    public byte readByte() {
        checkReadableBytes(1);
        return bytes[readerIndex++];
    }

    public byte[] readWord() {
        return readBytes(2);
    }

    public byte[] readDWord() {
        return readBytes(4);
    }

    public ByteArrayBuf markReaderIndex() {
        this.markedReaderIndex = this.readerIndex;
        return this;
    }

    public ByteArrayBuf resetReaderIndex() {
        readerIndex(markedReaderIndex);
        return this;
    }

    public int readerIndex() {
        return readerIndex;
    }

    public ByteArrayBuf readerIndex(int i) {
        if (i < 0 || i > bytes.length) throw new IndexOutOfBoundsException(String.format(
                "readerIndex: %d (expected: 0 <= readerIndex <= capacity(%d))", readerIndex, bytes.length));
        this.readerIndex = i;
        return this;
    }

    public int readableBytes() {
        return bytes.length - readerIndex;
    }

    public byte[] array() {
        return this.bytes;
    }

    public byte[] getBytes(int beginReaderIndex, int length) {

        if (beginReaderIndex < 0 || beginReaderIndex < 0 || beginReaderIndex > this.bytes.length || (beginReaderIndex + length) > this.bytes.length) {
            throw new IndexOutOfBoundsException(String.format("bytes length: %d, beginReaderIndex: %d, length: %d", this.bytes.length, beginReaderIndex, length));
        }

        byte[] newBytes = new byte[length];

        System.arraycopy(this.bytes, beginReaderIndex, newBytes, 0, length);

        return newBytes;
    }

    public byte[] getBytes(int i) {

        if (i < 0) throw new IllegalArgumentException("i must be greater than 0. " + "i:" + i);

        checkReadableBytes(i);

        byte[] copy = new byte[i];
        int length = Math.min(bytes.length - readerIndex, i);
        System.arraycopy(bytes, readerIndex, copy, 0, length);
        return copy;
    }

    public byte getByte(int index) {

        if (index < 0 || index > this.bytes.length) {
            throw new IndexOutOfBoundsException(String.format("the index is greater than the length of bytes. index: %d, bytes length: %d", index, this.bytes.length));
        }

        return this.bytes[index];
    }

    private void checkReadableBytes(int minimumReadableBytes) {
        if (bytes.length - readerIndex < minimumReadableBytes) {
            throw new IndexOutOfBoundsException(String.format("readableBytes is %d, it less than %d", (bytes.length - readerIndex), minimumReadableBytes));
        }
    }
}
