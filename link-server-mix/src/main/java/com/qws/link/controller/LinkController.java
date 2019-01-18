package com.qws.link.controller;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.header.FMHeader;
import com.qws.link.base.pakcet.BasePacket;
import com.qws.link.check.FMCheckPacket;
import com.qws.link.codec.CheckCode;
import com.qws.link.down.RemoteUpgradePacket;
import com.qws.link.entity.ReqCheckInfo;
import com.qws.link.entity.ReqHeader;
import com.qws.link.entity.ReqRemoteUpgrade;
import com.qws.link.message.fm.FMMessage;
import com.qws.link.message.gb.GBMessage;
import com.qws.link.message.base.LinkMessage;
import com.qws.link.send.SendServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiwenshuai
 * @note 升级接口
 * @since 19-1-5 15:58 by jdk 1.8
 */
@RestController
public class LinkController {


    private Logger logger = LoggerFactory.getLogger(LinkController.class);

    @GetMapping("/test")
    public String test() throws Exception {
        //模拟client发送消息
        byte[] headerBytes = ByteUtils.hexStringToBytes("23230201464d542d4345393837363534333231323301014c");
        byte[] body = ByteUtils.hexStringToBytes("120b0f0b2b260102030100000001c62e0d9527103f021011cc005302010103454e214e206900e627100500070932f402322e940601200e8d013d0e7401023601013507000000000000000005000b00000009000c0009000c000500550016003b0801010d952710005d00015d0e7f0e7f0e800e810e760e820e810e810e7a0e840e7e0e840e840e800e820e810e840e840e810e840e840e7f0e800e800e850e880e870e810e820e810e880e8d0e850e820e850e840e860e890e830e7f0e850e830e830e850e830e7c0e7a0e7c0e870e850e850e820e7b0e7c0e840e7d0e7f0e7d0e830e7a0e740e7e0e860e810e7e0e880e820e880e870e820e840e860e7f0e880e8b0e840e7f0e850e810e840e840e840e820e830e810e7c0e840e810e810e840e840e840e82090101001b353636353535363636363636363636363636363635363636363636b10003036400");
        byte bcc = CheckCode.mathParity(headerBytes, body);
        byte[] bytes = ByteUtils.addAll(headerBytes, body, bcc);
        //转换成bytebuf对象在netty之间进行传输
        LinkMessage build = new GBMessage().build(ByteArrayBuf.wrap(bytes));
        SendServer.send("test", build);
        return "成功";
    }


    @PostMapping("/sendUpgrade")
    public void sendUpgradeCommand(@RequestBody ReqRemoteUpgrade req) {
        RemoteUpgradePacket remoteUpgradePacket = new RemoteUpgradePacket();
        BeanUtils.copyProperties(req, remoteUpgradePacket);
        for (ReqHeader reqHeader : req.getReqHeaders()) {
            String code = reqHeader.getVin() == null ? reqHeader.getSn() : reqHeader.getVin();
            //命令的主动发起方 为0xfe
                remoteUpgradePacket.setUrl(req.getUrl());
            // 默认循环遍历
            try {
                LinkMessage linkMessage = message2bytes(remoteUpgradePacket, code,0x82);
                SendServer.send(code, linkMessage);
            } catch (Exception e) {
                logger.error("发送命令错误, code :{},exception:{}", code, e);
            }

        }
    }


    @PostMapping("/sendCheckInfo")
    public void sendCheckInfo(@RequestBody ReqCheckInfo req) {
        FMCheckPacket fmCheckPacket = new FMCheckPacket();
        BeanUtils.copyProperties(req, fmCheckPacket);
        for (ReqHeader reqHeader : req.getReqHeaders()) {
            String code = reqHeader.getVin() == null ? reqHeader.getSn() : reqHeader.getVin();
            // 默认循环遍历
            try {
                LinkMessage linkMessage = message2bytes(fmCheckPacket, code,0xB1);
                SendServer.send(code, linkMessage);
            } catch (Exception e) {
                logger.error("发送命令错误, code :{},exception:{}", code, e);
            }

        }
    }


    private LinkMessage message2bytes(BasePacket basePacket, String code,int command) throws Exception {
        byte[] fmPack = basePacket.unbuild();
        Integer dataLength = fmPack.length;
        FMHeader fmHeader = new FMHeader("!!",
                command,
                254,
                code,
                1,
                dataLength);
        byte[] fHeader = fmHeader.unbuild();
        byte bcc = CheckCode.mathParity(fHeader, fmPack);
        byte[] bytes = ByteUtils.addAll(fHeader, fmPack, bcc);
        return new FMMessage().build(ByteArrayBuf.wrap(bytes));
    }


}
