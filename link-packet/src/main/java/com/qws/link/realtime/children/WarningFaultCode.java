package com.qws.link.realtime.children;

/**
 * @author zhangsi
 * @date created in 2018/5/24 20:36
 */
public class WarningFaultCode {

    private Integer partId;

    private Integer faultSeq;

    public WarningFaultCode() {
    }

    public WarningFaultCode(Integer partId, Integer faultSeq) {
        this.partId = partId;
        this.faultSeq = faultSeq;
    }

    public Integer getPartId() {
        return partId;
    }

    public void setPartId(Integer partId) {
        this.partId = partId;
    }

    public Integer getFaultSeq() {
        return faultSeq;
    }

    public void setFaultSeq(Integer faultSeq) {
        this.faultSeq = faultSeq;
    }
}
