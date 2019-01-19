package com.qws.link.auto;

import com.qws.link.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 初始化服务
 **/
public class InitServer {


    @Autowired
    NettyServer nettyServer;



    public void open() {
        nettyServer.initNetty();
    }


    public void close() {
        if (nettyServer != null) {
            nettyServer.shutdown();
        }
    }

}
