package com.qws.link.config;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-5 15:32 by jdk 1.8
 */
public class InitBean {

    private int port ;

    private String serverName ;

    private boolean keepalive ;

    private boolean reuseaddr ;

    private boolean tcpNodelay ;

    private int backlog ;

    private  int  sndbuf ;

    private int revbuf ;

    private int heart ;

    private boolean ssl ;

    private String jksFile;

    private String jksStorePassword;

    private String jksCertificatePassword;


    private int  initalDelay ;

    private  int period ;

    private int bossThread;

    private int workThread;


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
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

    public int getHeart() {
        return heart;
    }

    public void setHeart(int heart) {
        this.heart = heart;
    }

    public boolean isSsl() {
        return ssl;
    }

    public void setSsl(boolean ssl) {
        this.ssl = ssl;
    }

    public String getJksFile() {
        return jksFile;
    }

    public void setJksFile(String jksFile) {
        this.jksFile = jksFile;
    }

    public String getJksStorePassword() {
        return jksStorePassword;
    }

    public void setJksStorePassword(String jksStorePassword) {
        this.jksStorePassword = jksStorePassword;
    }

    public String getJksCertificatePassword() {
        return jksCertificatePassword;
    }

    public void setJksCertificatePassword(String jksCertificatePassword) {
        this.jksCertificatePassword = jksCertificatePassword;
    }

    public int getInitalDelay() {
        return initalDelay;
    }

    public void setInitalDelay(int initalDelay) {
        this.initalDelay = initalDelay;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
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
