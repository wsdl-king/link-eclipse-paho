package com.qws.link.realtime.children;

import com.alibaba.fastjson.JSON;
import com.qws.link.ByteUtils;
import com.qws.link.base.ByteArrayBuf;
import com.qws.link.base.pakcet.GBPacket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * 0xB1 OTA升级状态数据
 * 这个实体对应的是OTA上报节点状态--自定义
 */
public class OtaNodePacket implements GBPacket, Serializable {

    private static final Logger logger = LoggerFactory.getLogger(OtaNodePacket.class);
    private static final long serialVersionUID = -1249923222310744505L;
    /**
     * 节点类型
     */
    private Integer nodeType;

    /**
     * 节点状态
     */
    private Integer nodeStatus;

    /**
     * 下载进度
     */
    private Integer downProgress;

    public Integer getNodeType() {
        return nodeType;
    }

    public void setNodeType(Integer nodeType) {
        this.nodeType = nodeType;
    }

    public Integer getNodeStatus() {
        return nodeStatus;
    }

    public void setNodeStatus(Integer nodeStatus) {
        this.nodeStatus = nodeStatus;
    }

    public Integer getDownProgress() {
        return downProgress;
    }

    public void setDownProgress(Integer downProgress) {
        this.downProgress = downProgress;
    }

    @Override
    public void build(ByteArrayBuf byteBuf) throws Exception {
        int index = byteBuf.readerIndex();

        byteBuf.readWord();

        this.setNodeType(ByteUtils.toIntWithValid(byteBuf.readByte()));

        if (this.getNodeType() == 3) {
            this.setDownProgress(ByteUtils.toInt(byteBuf.readByte()));
        }
        this.setNodeStatus(ByteUtils.toIntWithValid(byteBuf.readByte()));

        logger.info("[RealtimeDataPacket.OtaNodePacket] bytes:{}, data:{}"
                , ByteUtils.asHex(byteBuf.getBytes(index, byteBuf.readerIndex() - index))
                , JSON.toJSONString(this));
    }

    @Override
    public byte[] unbuild() {
        //此处不需要我 实体 tobe bytes[] 所以写成默认的bytes[0]
        return new byte[0];
    }

    @Override
    public Integer length() {
        return null;
    }
}
