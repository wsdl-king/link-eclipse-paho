package com.qws.link.netty.handler;

import com.qws.link.SpringBeanUtils;
import com.qws.link.entity.ChannelAttr;
import com.qws.link.entity.ChannelMap;
import com.qws.link.handler.holder.MessageHolder;
import com.qws.link.message.base.LinkMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author qiwenshuai
 * @note 这个应该是我用来处理业务逻辑的东东，不过之前呢， 我需要解码成我需要的bytebuf
 * @since 19-1-5 15:35 by jdk 1.8
 */
@ChannelHandler.Sharable
public class ServerHandler extends SimpleChannelInboundHandler<LinkMessage> {

    /**
     * 此处使用Channel级别的AbstractMap存储额外属性,
     * tip1:ChannelHandlerContext 级别的 AbstractMap 单体享用,互不影响
     * tip2:ChannelHandlerContext.Channel()级别的 AbstractMap 全局享用,互相影响.
     */
    private static final String CHANNEL = "netty.channel";
    private Logger logger = LoggerFactory.getLogger(ServerHandler.class);


    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        logger.info("Channel  already completed ");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        //通道关闭的操作
        AttributeKey<Object> objectAttributeKey = AttributeKey.valueOf(String.valueOf(CHANNEL));
        ChannelAttr channelAttr = (ChannelAttr) ctx.channel().attr(objectAttributeKey).get();
        String sn = channelAttr.getSn();
        ChannelMap.removeChannel(sn);
        logger.info("close success,address is: {}", ctx.channel().localAddress());
    }

    /**
     * 在(Encoder)在MessageHandler后方禁止使用ChannelHandlerContext.writeAndFlush
     * ChannelHandlerContext.writeAndFlush会跳过Encoder
     * 推荐使用ChannelHandlerContext.channel().writeAndFlush,这里会按你定义的顺序进行调用
     * decoder(接受,是一个InboundHandler)->ChannelInboundHandler(自定义读消息handler)->encoder(发出,其实是一个outboundHandler)
     * 参考 1: https://blog.csdn.net/wm3418925/article/details/54864177
     * 参考 2: https://www.jianshu.com/p/a8a0acfdc96c
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            //这边需要处理超时的操作
            AttributeKey<Object> objectAttributeKey = AttributeKey.valueOf(String.valueOf(CHANNEL));
            ChannelAttr channelAttr = (ChannelAttr) ctx.channel().attr(objectAttributeKey).get();
            String deviceId = channelAttr.getDeviceId();
            logger.info("Sorry, I have timed out,My deviceId is {}", deviceId);
        }
        super.userEventTriggered(ctx, evt);
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LinkMessage msg) {
        MessageHolder messageHolder = SpringBeanUtils.getBean(MessageHolder.class);
        messageHolder.messageArrived(1000L, msg,ctx.channel());
    }
}