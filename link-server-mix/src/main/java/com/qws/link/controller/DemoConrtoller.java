package com.qws.link.controller;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.codec.CheckCode;
import com.qws.link.message.gb.GBMessage;
import com.qws.link.message.base.LinkMessage;
import com.qws.link.send.SendServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-5 15:58 by jdk 1.8
 */
@RestController
public class DemoConrtoller {


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
}
