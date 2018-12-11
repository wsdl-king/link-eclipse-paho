package com.qws.link.packet.warn;


import com.qws.link.ByteUtils;

/**
 * 提供方法进行告警验证
 */
public abstract class BaseWarnPacket {
	
	public abstract byte[] bytes();
	
	public String checkWarn(byte[] bytes){
		StringBuilder sb =new StringBuilder();
		byte[] srcBytes = bytes();
		for(int i=0;i<srcBytes.length;i++){
			if(srcBytes[i]!=bytes[i]){
				byte temp = (byte)(srcBytes[i]^bytes[i]);
				if(temp!=0){
					byte[] tempBytes = ByteUtils.byte2BitArray(temp);
					byte[] srcTempBytes = ByteUtils.byte2BitArray(srcBytes[i]);
					for(int j=0;j<8;j++){
						if(tempBytes[j]==1){
							sb.append(srcTempBytes[j]!=(byte)0?"":"-");
							sb.append(type());
							sb.append(i*8+j);
							sb.append(";");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	protected String checkWarn(byte[] bytes,byte[] srcBytes){
		StringBuilder sb =new StringBuilder();
		for(int i=0;i<srcBytes.length;i++){
			if(srcBytes[i]!=bytes[i]){
				byte temp = (byte)(srcBytes[i]^bytes[i]);
				if(temp!=0){
					byte[] tempBytes = ByteUtils.byte2BitArray(temp);
					byte[] srcTempBytes = ByteUtils.byte2BitArray(srcBytes[i]);
					for(int j=0;j<8;j++){
						if(tempBytes[j]==1){
							sb.append(srcTempBytes[j]!=(byte)0?"":"-");
							sb.append(type());
							sb.append(i*8+j);
							sb.append(";");
						}
					}
				}
			}
		}
		return sb.toString();
	}
	
	public abstract String type();
}
