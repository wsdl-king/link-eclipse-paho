package com.qws.link.down;

import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.FMPacket;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * 空中升级包
 */
public class RemoteUpgradePacket implements FMPacket, Serializable {

    private static final byte SEP = 0x3b;   // ";"

    private static final long serialVersionUID = -2504613380429123123L;
    /**
     * 拨号点名称
     */
    private String dialPoint;
    /**
     * 拨号用户名
     */
    private String dialUser;
    /**
     * 拨号密码
     */
    private String dialPwd;
    /**
     * 地址
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 生产厂商代码
     */
    private String manufacturer;
    /**
     * 硬件版本
     */
    private String hardwareVersion;
    /**
     * 固件版本
     */
    private String firmwareVersion;
    /**
     * URL地址
     */
    private String url;
    /**
     * 连接到升级服务器时限
     */
    private Integer timeLimit;

    public String getDialPoint() {
        return dialPoint;
    }

    public void setDialPoint(String dialPoint) {
        this.dialPoint = dialPoint;
    }

    public String getDialUser() {
        return dialUser;
    }

    public void setDialUser(String dialUser) {
        this.dialUser = dialUser;
    }

    public String getDialPwd() {
        return dialPwd;
    }

    public void setDialPwd(String dialPwd) {
        this.dialPwd = dialPwd;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getFirmwareVersion() {
        return firmwareVersion;
    }

    public void setFirmwareVersion(String firmwareVersion) {
        this.firmwareVersion = firmwareVersion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    @Override
    public void build(ByteArrayBuf buf) throws Exception {

    }

    /**
     * 指令如下：
     * “URL地址;拨号点名称;拨号用户名;拨号密码;地址;端口;生产厂商代码;硬件版本;固件版本；连接到升级服务器时限”，
     * 若某个参数无值，则为空。远程升级操作建议但不限于采用FTP方式进行操作。
     */
    @Override
    public byte[] unbuild() {
        byte[] resultBytes = new byte[0];

        // url
        if (StringUtils.isNotBlank( this.getUrl())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getUrl().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 拨号点名称
        if (StringUtils.isNotBlank(this.getDialPoint())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getDialPoint().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 拨号用户名
        if (StringUtils.isNotBlank(this.getDialUser())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getDialUser().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 拨号密码
        if (StringUtils.isNotBlank(this.getDialPwd())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getDialPwd().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 地址
        if (StringUtils.isNotBlank(this.getHost())) {

            String[] s = this.getHost().split("\\.");

            byte[] ip = new byte[6];
            if (s.length == 4) {
                ip[0] = 0;
                ip[1] = 0;
                for (int i = 0; i < 4; i++) {
                    ip[i+2] = ByteUtils.intToByteArray(Integer.valueOf(s[i]))[3];
                }
            } else if (s.length == 6) {
                for (int i = 0; i < 6; i++) {
                    ip[i] = ByteUtils.intToByteArray(Integer.valueOf(s[i]))[3];
                }
            } else {
                throw new RuntimeException("错误的ip地址: " + this.getHost());
            }

            resultBytes = ByteUtils.addAll(resultBytes, ip);
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 端口
        if (this.getPort() != null) {
            resultBytes = ByteUtils.addAll(resultBytes, ByteUtils.intToByteArray2(this.getPort()));
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 生产厂商代码
        if (StringUtils.isNotBlank(this.getManufacturer())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getManufacturer().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 硬件版本
        if (StringUtils.isNotBlank(this.getHardwareVersion())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getHardwareVersion().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 固件版本
        if (StringUtils.isNotBlank(this.getFirmwareVersion())) {
            resultBytes = ByteUtils.addAll(resultBytes, this.getFirmwareVersion().getBytes());
        }
        resultBytes = ByteUtils.addAll(resultBytes, SEP);

        // 连接到升级服务器时限
        if (this.getTimeLimit() != null) {
            resultBytes = ByteUtils.addAll(resultBytes, ByteUtils.intToByteArray2(this.getTimeLimit()));
        }

        return resultBytes;
    }

    @Override
    public Integer length() {
        return null;
    }

}
