package com.qws.link.entity;

import com.alibaba.fastjson.JSON;

/**
 * @author qiwenshuai
 * @note
 * @since 19-1-18 11:17 by jdk 1.8
 */
public class ReqCheckInfo {

    /**
     * 设备信息请求头
     */
    private ReqHeader[] reqHeaders;

    /**
     * 命令下发时间 6
     */
    private Long downTime;
    /**
     * 校验数据 1
     * 0x01 可以升级
     * 0x02 无效设备
     * 0x03 升级协议未知
     * 0x04 无升级任务
     * 0x05 无需升级(>=版本号)
     * 0x06 异常原因不可升级
     * */
    private Integer checkValue;

    /**
     * 附加数据长度 1
     */
    private Integer attachLength;

    /**
     * 附加数据 x
     */
    private String attachValue;


    public ReqHeader[] getReqHeaders() {
        return reqHeaders;
    }

    public void setReqHeaders(ReqHeader[] reqHeaders) {
        this.reqHeaders = reqHeaders;
    }

    public Long getDownTime() {
        return downTime;
    }

    public void setDownTime(Long downTime) {
        this.downTime = downTime;
    }

    public Integer getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(Integer checkValue) {
        this.checkValue = checkValue;
    }

    public Integer getAttachLength() {
        return attachLength;
    }

    public void setAttachLength(Integer attachLength) {
        this.attachLength = attachLength;
    }

    public String getAttachValue() {
        return attachValue;
    }

    public void setAttachValue(String attachValue) {
        this.attachValue = attachValue;
    }

    public static void main(String[] args) {
        ReqCheckInfo reqCheckInfo = new ReqCheckInfo();
        ReqHeader[] reqHeaders = new ReqHeader[1];
        ReqHeader reqHeader = new ReqHeader();
        reqHeader.setSn("fm123456789012345");
        reqHeader.setVin("fm012345678901234");
        reqHeaders[0]=reqHeader;
        reqCheckInfo.setReqHeaders(reqHeaders);
        reqCheckInfo.setDownTime(20181115114338L);
        reqCheckInfo.setAttachLength(0);
        reqCheckInfo.setCheckValue(1);
        reqCheckInfo.setAttachValue("");
        String s = JSON.toJSONString(reqCheckInfo);
        System.out.println(s);
    }
}
