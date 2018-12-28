package com.qws.link.realtime.extend;

import com.qws.link.realtime.base.RealTimePacket;
import com.qws.link.realtime.children.OtaNodePacket;

/**
 * @author qiwenshuai
 * @code: 0xB1
 * @note 这里基于国标协议实时数据上报扩展了OTA升级节点上报的数据, 在此项目中, 可能更多的使用此项目
 * @since 18-12-28 16:11 by jdk 1.8
 */
public class RealTimeExtendPacket extends RealTimePacket {

    /**
     * OTA升级节点上报--- 此信息为自定义扩展的数据
     */
    protected OtaNodePacket otaNodePacket;

}
