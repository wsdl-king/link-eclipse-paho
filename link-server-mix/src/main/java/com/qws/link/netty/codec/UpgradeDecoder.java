package com.qws.link.netty.codec;

import com.qws.link.base.ByteArrayBuf;
import com.qws.link.mqtt.gb.GBMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;


/**
 * @author qiwenshuai
 * @note 升级解码器，此处我采用netty自带的LengthFieldBasedFrameDecoder实现粘包/半包的解码操作
 * @since 19-1-5 16:27 by jdk 1.8
 */
public class UpgradeDecoder extends LengthFieldBasedFrameDecoder {

    /**
     * @param maxFrameLength      帧的最大长度
     * @param lengthFieldOffset   length字段偏移的地址
     * @param lengthFieldLength   length字段所占的字节长
     * @param lengthAdjustment    修改帧数据长度字段中定义的值，可以为负数 因为有时候我们习惯把头部记入长度,若为负数,则说明要推后多少个字段
     * @param initialBytesToStrip 解析时候跳过多少个长度
     * @param failFast            为true，当frame长度超过maxFrameLength时立即报TooLongFrameException异常，为false，读取完整个帧再报异
     */
    public UpgradeDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip, boolean failFast) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip, failFast);
    }

    @Override
    protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
        //1 调用父类(LengthFieldBasedFrameDecoder)方法:
        // 此处netty已经直接为我们进行了规定长度解码,直接返回给我需要的byte[]就OK了
        ByteBuf frame = (ByteBuf) super.decode(ctx, in);
        if (frame == null) {
            return null;
        }
        byte[] bytes = new byte[frame.readableBytes()];
        //将bytebuf中的数据拷贝到字节数组中
        frame.readBytes(bytes);
        return new GBMessage().build(ByteArrayBuf.wrap(bytes));
    }

}
