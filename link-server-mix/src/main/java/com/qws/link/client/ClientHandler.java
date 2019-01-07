package com.qws.link.client;

import com.qws.link.ByteUtils;
import com.qws.link.codec.CheckCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-7 10:35 by jdk 1.8
 */
public class ClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("客户端链接完成");
        byte[] headerBytes = ByteUtils.hexStringToBytes("23230201464d542d4345393837363534333231323301014c");
        byte[] body = ByteUtils.hexStringToBytes("120b0f0b2b260102030100000001c62e0d9527103f021011cc005302010103454e214e206900e627100500070932f402322e940601200e8d013d0e7401023601013507000000000000000005000b00000009000c0009000c000500550016003b0801010d952710005d00015d0e7f0e7f0e800e810e760e820e810e810e7a0e840e7e0e840e840e800e820e810e840e840e810e840e840e7f0e800e800e850e880e870e810e820e810e880e8d0e850e820e850e840e860e890e830e7f0e850e830e830e850e830e7c0e7a0e7c0e870e850e850e820e7b0e7c0e840e7d0e7f0e7d0e830e7a0e740e7e0e860e810e7e0e880e820e880e870e820e840e860e7f0e880e8b0e840e7f0e850e810e840e840e840e820e830e810e7c0e840e810e810e840e840e840e82090101001b353636353535363636363636363636363636363635363636363636b10003036400");
        byte bcc = CheckCode.mathParity(headerBytes, body);
        byte[] bytes = ByteUtils.addAll(headerBytes, body, bcc);
        //转换成bytebuf对象在netty之间进行传输
        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        ctx.writeAndFlush(byteBuf);
        System.out.println("客户端消息发送完成");
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.err.println("Client: " + msg);

    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.err.println("----------客户端数据读异常-----------");
        ctx.close();
    }

    public static void main(String[] args) {
        byte[] headerBytes = ByteUtils.hexStringToBytes("23230201464d542d4345393837363534333231323301014c");
        byte[] body = ByteUtils.hexStringToBytes("120b0f0b2b260102030100000001c62e0d9527103f021011cc005302010103454e214e206900e627100500070932f402322e940601200e8d013d0e7401023601013507000000000000000005000b00000009000c0009000c000500550016003b0801010d952710005d00015d0e7f0e7f0e800e810e760e820e810e810e7a0e840e7e0e840e840e800e820e810e840e840e810e840e840e7f0e800e800e850e880e870e810e820e810e880e8d0e850e820e850e840e860e890e830e7f0e850e830e830e850e830e7c0e7a0e7c0e870e850e850e820e7b0e7c0e840e7d0e7f0e7d0e830e7a0e740e7e0e860e810e7e0e880e820e880e870e820e840e860e7f0e880e8b0e840e7f0e850e810e840e840e840e820e830e810e7c0e840e810e810e840e840e840e82090101001b353636353535363636363636363636363636363635363636363636b10003036400");
        byte bcc = CheckCode.mathParity(headerBytes, body);
        byte[] a =new byte[1];
        a[0]=bcc;
        System.out.println(ByteUtils.asHex(a));
    }
}
