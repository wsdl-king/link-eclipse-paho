package com.qws.link.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author qiwenshuai
 * @note 外部配置
 * @since 19-1-5 15:32 by jdk 1.8
 */
@Component
public class InitBean {

    @Value("${activelink.port}")
    private int port;
    @Value("${activelink.keepalive}")
    private boolean keepalive;
    @Value("${activelink.reuseaddr}")
    private boolean reuseaddr;
    @Value("${activelink.tcpNodelay}")
    private boolean tcpNodelay;
    @Value("${activelink.backlog}")
    private int backlog;
    @Value("${activelink.sndbuf}")
    private int sndbuf;
    @Value("${activelink.revbuf}")
    private int revbuf;
    @Value("${activelink.bossThread}")
    private int bossThread;
    @Value("${activelink.workThread}")
    private int workThread;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isKeepalive() {
        return keepalive;
    }

    public void setKeepalive(boolean keepalive) {
        this.keepalive = keepalive;
    }

    public boolean isReuseaddr() {
        return reuseaddr;
    }

    public void setReuseaddr(boolean reuseaddr) {
        this.reuseaddr = reuseaddr;
    }

    public boolean isTcpNodelay() {
        return tcpNodelay;
    }

    public void setTcpNodelay(boolean tcpNodelay) {
        this.tcpNodelay = tcpNodelay;
    }

    public int getBacklog() {
        return backlog;
    }

    public void setBacklog(int backlog) {
        this.backlog = backlog;
    }

    public int getSndbuf() {
        return sndbuf;
    }

    public void setSndbuf(int sndbuf) {
        this.sndbuf = sndbuf;
    }

    public int getRevbuf() {
        return revbuf;
    }

    public void setRevbuf(int revbuf) {
        this.revbuf = revbuf;
    }

    public int getBossThread() {
        return bossThread;
    }

    public void setBossThread(int bossThread) {
        this.bossThread = bossThread;
    }

    public int getWorkThread() {
        return workThread;
    }

    public void setWorkThread(int workThread) {
        this.workThread = workThread;
    }
}
