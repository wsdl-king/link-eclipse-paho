package com.qws.link.base.pakcet;


/**
 * 国标基础内容包接口
 */
public interface GBPacket extends BasePacket {


    //接口可以写默认实现,所以可以把一些对于方法内部辅助的接口方法写到此
    default void test() {
        System.out.println("辅助的方法");
    }

}
