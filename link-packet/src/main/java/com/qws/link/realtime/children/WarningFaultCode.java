package com.qws.link.realtime.children;


import java.io.Serializable;

/**
 */
public class WarningFaultCode implements Serializable {

    private static final long serialVersionUID = -5440099714258588148L;
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
