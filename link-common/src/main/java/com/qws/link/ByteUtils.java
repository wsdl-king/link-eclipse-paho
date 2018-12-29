package com.qws.link;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

/**
 * 字节工具类
 * Created by lemanli on 2017/1/24.
 */
public class ByteUtils {

    public static byte BYTE_FF = (byte) 0xFF;
    public static byte[] WORD_FF = new byte[]{(byte)0xFF, (byte)0xFF};
    public static byte[] SWORD_FF = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF};
    public static byte[] DWORD_FF = new byte[]{(byte)0xFF, (byte)0xFF, (byte)0xFF, (byte)0xFF};

	/**
	 * 字符串转换成一个使用给定字符集的字节序列，
	 * 并将结果存储到一个新的字节数组
	 * 
	 * @param str：字符串
	 * @param charset:指定字符集，例如"ASCII"
	 **/
    public static byte[] writeString2Bytes(String str, Charset charset, int length) {
        if (str.length() > length) return null;
        byte[] bytes = new byte[length];

        byte[] src = str.getBytes(charset == null ? Charset.defaultCharset() : charset);
        System.arraycopy(src, 0, bytes, 1, src.length);
        return bytes;
    }
    
    /**
	 * 字节组转换成Double 
	 * @param diff:差值
	 * @return
	 */
	public static int toInt(byte a,int diff) {
		return (0xff & a)-diff;
	}

	public static int toInt(byte a) {
	    return (0xff & a);
    }

	/**
	 * 带验证的字节转int
	 * @param a
	 * @param diff	差值
	 * @return
	 */
    public static Integer toIntWithValid(byte a, int diff) {
        return isCorrectData(a) ? toInt(a, diff) : null;
    }

    public static Integer toIntWithValid(byte a) {
        return isCorrectData(a) ? toInt(a) : null;
    }

    /**
     *
     * @param b
     * @param offset: 4-b.length
     * @return
     */
    public static Integer toIntWithValid(byte[] b, int offset) {
        return isCorrectData(b) ? byteArrayToInt(b, offset) : null;
    }

    public static Integer toIntWithValid(byte[] b, int offset, int diff) {
    	return isCorrectData(b) ? byteArrayToInt(b, offset) - diff : null;
	}

    /**
     * 字节数组转换成Int
     * @param b
     * @param offset :4-b.length
     * @return
     */
    public static int byteArrayToInt(byte[] b, int offset) {
		byte[] arr = new byte[4];
		for (int i = 0; i < offset; i++) {
			arr[i] = 0x00;
		}
		System.arraycopy(b, 0, arr, offset, b.length);
		offset = 0;
		int value = 0;
		for (int i = 0; i < 4; i++) {
			int shift = (4 - 1 - i) * 8;
			value += (arr[i + offset] & 0x000000FF) << shift;
		}
		return value;
	}

   /**
    * 字符串转换成字节数组
    * @param hexString
    * @return
    */
    public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) ("0123456789ABCDEF".indexOf(hexChars[pos]) << 4
					| "0123456789ABCDEF".indexOf(hexChars[pos + 1]));
		}
		return d;
	}
    
    /**
	 * 字符串转换成字节
	 **/
    public static byte[] string2Bytes(String str) {
		byte[] result = new byte[str.length()];
		for (int i = 0; i < str.length(); i++) {
			result[i] = (byte) str.charAt(i);
		}
		return result;
	}
    
    /**
	 * 字符串转换成指定长度的字节,字符串长度不足时，用 0 向前补位
	 **/
    public static byte[] string2Bytes(String str,Integer length) {
    	 byte[] vinBytes = new byte[length];
    	 byte[] vinBytes_realy = str.getBytes();
    		for (int i = 0; i < vinBytes_realy.length; i++) {
    			if(i<vinBytes.length){
    				vinBytes[vinBytes.length-i-1] = vinBytes_realy[vinBytes_realy.length+-i-1];
    			}else{
    				break;
    			}
    		}
		return vinBytes;
	}
    


    /**
   	 * 字节转换成字节数组
   	 **/
	public static byte[] byte2BitArray(byte b) {
		byte[] array = new byte[8];
		for (int i = 0; i < 8; i++) {
			array[i] = (byte) (b & 1);
			b = (byte) (b >> 1);
		}
		return array;
	}

	/***
	 * 转化二进制数组为时间对象
	 * 
	 * @param data
	 */
	public static Date dateBytes2Date(byte[] data) throws Exception {
		String dateStr = "20" + (data[0]<10?"0"+data[0]:""+data[0]) + (data[1]<10?"0"+data[1]:""+data[1]) + (data[2]<10?"0"+data[2]:""+data[2])+
				(data[3]<10?"0"+data[3]:""+data[3]) + (data[4]<10?"0"+data[4]:""+data[4]) + (data[5]<10?"0"+data[5]:""+data[5]);
		return DateTimeUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
	}
	
	/***
	 * 转化二进制数组为yyyyMMddHHmmss格式的数据
	 * 
	 * @param data
	 */
	public static Long dateBytes2Long(byte[] data) throws Exception {
		String dateStr = "20" + (data[0]<10?"0"+data[0]:""+data[0]) + (data[1]<10?"0"+data[1]:""+data[1]) + (data[2]<10?"0"+data[2]:""+data[2])+
				(data[3]<10?"0"+data[3]:""+data[3]) + (data[4]<10?"0"+data[4]:""+data[4]) + (data[5]<10?"0"+data[5]:""+data[5]);
		return Long.valueOf(dateStr);
	}
	
	/***
	 * 转化二进制数组为时间字符串
	 * 
	 * @param data
	 * @return 例如，{0x0f,0x0b,0x16,0x0d,0x37,0x2e} 返回 2015-11-22 13:55:46
	 */
	public static String convertByteArrtDatetimeStr(byte[] data) {
		return "20" + data[0] + "-" + data[1] + "-" + data[2] + " " + data[3] + ":" + data[4] + ":" + data[5];
	}
	
	/** 拼接对象成字节数组 */
	public static byte[] addAll(Object... objs) {
		int length = 0;
		for (Object obj : objs) {
			if (obj.getClass().isArray()) {
				byte[] objArr = (byte[]) obj;
				length += objArr.length;
			} else {
				length += 1;
			}
		}
		byte[] result = new byte[length];
		int pos = 0;
		for (Object obj : objs) {
			if (obj.getClass().isArray()) {
				byte[] objArr = (byte[]) obj;
				System.arraycopy(objArr, 0, result, pos, objArr.length);
				pos += objArr.length;
			} else {
				result[pos] = (byte)obj;
				pos += 1;
			}
		}
		return result;
	}
	
	/** 
	 * 整型转换成指定长度的字节数组 
	 * 
	 * @param length：字节的长度
	 * */
	public static byte[] intToByteArray(final int integer, int length) {
		byte[] byteArray = new byte[length];
		for (int i = 0; i < length; i++){
			byteArray[i]=(byte)(integer >>> ((length-1-i)*8));
		}
		return byteArray;
	}
	
	/** 整型转换成字节数组 */
	public static byte[] intToByteArray(final int integer) {
		int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
		byte[] byteArray = new byte[4];

		for (int n = 0; n < byteNum; n++)
			byteArray[3 - n] = (byte) (integer >>> (n * 8));

		return (byteArray);
	}

	public static byte integerToByte(Integer integer) {
		return integer == null ? 0 : integer.byteValue();
	}
	
	/** 整型转换成字节数组 */
	public static byte[] intToByteArray2(final int integer) {
		int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
		byte[] byteArray = new byte[4];

		for (int n = 0; n < byteNum; n++)
			byteArray[3 - n] = (byte) (integer >>> (n * 8));
		return new byte[] { byteArray[2], byteArray[3] };
	}

	/** 字节组转换成Double 
	 *  @param der:除数
	 *  @param diff:差值
	 **/
	public static Double toDouble(byte a, byte b, byte c, byte d, double der, int diff) {
		int temp = ByteUtils.byteArrayToInt(new byte[] { a, b, c, d }, 0);
		return BigDecimal.valueOf(temp).divide(BigDecimal.valueOf(der)).subtract(BigDecimal.valueOf(diff))
				.doubleValue();
	}
	
	/**
	 * 字节组转换成Double 
	 * @param der:除数
	 * @param diff:差值
	 * @return
	 */
	public static Double toDouble(byte a, double der, double diff) {
		int temp = 0xff & a;
		return BigDecimal.valueOf(temp).divide(BigDecimal.valueOf(der)).subtract(BigDecimal.valueOf(diff))
				.doubleValue();
	}

	/**
	 * 字节组转换成Double 
	 * @param der:除数
	 * @param diff:差值
	 * @return
	 */
	public static Double toDouble(byte a, byte b, double der, int diff) {
		int temp = ByteUtils.byteArrayToInt(new byte[] { 0x00, 0x00, a, b }, 0);
		return BigDecimal.valueOf(temp).divide(BigDecimal.valueOf(der)).subtract(BigDecimal.valueOf(diff))
				.doubleValue();
	}
	
	/**
	 * 字节组转换成Double 
	 * @param der:除数
	 * @param diff:差值
	 * @return
	 */
	public static Double toDouble(byte[] bytes, double der, int diff) {
		int temp = 0;
		if(bytes.length==1){
			temp = 0xff & bytes[0];
		}else if(bytes.length==2){
			temp = ByteUtils.byteArrayToInt(new byte[] { 0x00, 0x00, bytes[0], bytes[1] }, 0);
		}else if(bytes.length==4){
			temp = ByteUtils.byteArrayToInt(bytes, 0);
		}
		return BigDecimal.valueOf(temp).divide(BigDecimal.valueOf(der)).subtract(BigDecimal.valueOf(diff))
				.doubleValue();
	}

	public static Double toDoubleWithValid(byte[] bytes, double der, int diff) {
        return isCorrectData(bytes) ? toDouble(bytes, der, diff) : null;
    }

    public static int doubleToInt(double d, int multiplier, int addend) {
        return (int)(d*multiplier)+addend*multiplier;
    }

    public static byte[] doubleToByteArray(double d, int multiplier, int addend, int length) {
        return intToByteArray(doubleToInt(d, multiplier, addend), length);
    }

    public static byte[] doubleToByteArray(double d, int multiplier, int addend) {
	    return intToByteArray(doubleToInt(d, multiplier, addend));
    }

    public static byte[] doubleToByteArrayFF(Double d, int multiplier, int addend) {
        return d == null ? DWORD_FF : doubleToByteArray(d, multiplier, addend);
    }

	/**
	 * 计算公式：d*multiplier + addend*multiplier
	 * @param d
	 * @param multiplier	乘数
	 * @param addend		加数
	 * @param length	字节长度
	 * @return
	 */
    public static byte[] doubleToByteArrayFF(Double d, int multiplier, int addend, int length) {
        if (d == null) return returnFF(length);
        return doubleToByteArray(d, multiplier, addend, length);
    }

	/**
	 * 计算公式：d*multiplier + addend
	 * @param d
	 * @param multiplier
	 * @param addend
	 * @param length
	 * @return
	 */
	public static byte[] doubleToByteArrayFF2(Double d, int multiplier, int addend, int length) {
		if (d == null) return returnFF(length);
		return intToByteArray((int)(d*multiplier)+addend, length);
	}

	/**
	 * 时间对象转换成字节数组
	 **/
	public static byte[] datetime2ByteArr(Date date) {
		String datetime = DateTimeUtils.formatDate(date);
		byte[] data = new byte[6];
		String[] strArr = datetime.trim().split(" ");
		int i = 0;
		for (String s : strArr[0].split("-")) {
			data[i++] = (byte) (Integer.parseInt(s) % 100);
		}
		for (String s : strArr[1].split(":")) {
			data[i++] = (byte) Integer.parseInt(s);
		}
		return data;
	}
	
	public static Date bytes2Date(byte[] bytes) throws ParseException{
		if(bytes.length!=6){
			return null;
		}
		Integer year = 0xff & bytes[0];
		Integer month = 0xff & bytes[1];
		Integer day = 0xff & bytes[2];
		Integer hour = 0xff & bytes[3];
		Integer minute = 0xff & bytes[4];
		Integer second = 0xff & bytes[5];
		String dateStr = "20"+year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateStr);
	}

    public static byte[] longTimeToByteArray(long dateTime) {

        String str = String.valueOf(dateTime);

        int year = Integer.valueOf(str.substring(2, 4));
        int month = Integer.valueOf(str.substring(4, 6));
        int day = Integer.valueOf(str.substring(6, 8));
        int hour = Integer.valueOf(str.substring(8, 10));
        int minute = Integer.valueOf(str.substring(10, 12));
        int second = Integer.valueOf(str.substring(12, 14));

        return new byte[]{(byte)year, (byte)month, (byte)day, (byte)hour, (byte)minute, (byte)second};
    }

    public static byte[] localDateTimeToByteArray(LocalDateTime localDateTime) {

        int year = localDateTime.getYear() % 100;
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        int second = localDateTime.getSecond();

        return new byte[]{(byte)year, (byte)month, (byte)day, (byte)hour, (byte)minute, (byte)second};
    }
	
	/** 将字节数组转换成16进制并以字符串方式输出 */
	public static String asHex(byte[] bytes, String separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			String code = Integer.toHexString(bytes[i] & 0xFF);
			if ((bytes[i] & 0xFF) < 16) {
				sb.append('0');
			}

			sb.append(code);

			if (separator != null && i < bytes.length - 1) {
				sb.append(separator);
			}
		}

		return sb.toString();
	}

	public static String asHex(byte[] bytes) {
	    return asHex(bytes, null);
    }

	/** 判断是否为有效数据包, 数据包为FF...FF, 为无效数据包*/
	public static boolean isCorrectData(byte[] bytes) {

		for (int i = 0; i < bytes.length; i++) {

			if (toInt(bytes[i], 0) != 255 && toInt(bytes[i], 0) != 254) {
				return true;
			}
		}

		return false;
	}

	/** 判断是否为有效数据包, 数据包为FF, 为无效数据包*/
	public static boolean isCorrectData(byte b) {

		if (toInt(b, 0) != 255 && toInt(b, 0) != 254) return true;

		return false;
	}

	public static byte integerToByteAutoZero(Integer integer) {
        return integer == null ? 0 : integer.byteValue();
    }

    public static byte integerToByteAutoFF(Integer integer) {
	    return integer == null ? BYTE_FF : integer.byteValue();
    }

    public static byte integerToByteAutoFF(Integer integer, int added) {
	    return integer == null ? BYTE_FF : (byte)(integer + added);
    }

    public static byte[] integerToByteArrayFF(Integer integer, int length) {

	   if (integer == null) return returnFF(length);

	    return intToByteArray(integer, length);
    }

    public static byte[] integerToByteArrayFF(Integer integer, int added, int length) {
        if (integer == null) return returnFF(length);
        return intToByteArray(integer + added, length);
    }

    public static String getStringCutZeroByte(byte[] bytes) {
		Integer index = null;
		for (int i = 0; i < bytes.length; i++) {
			if (bytes[i] != 0) {
				index = i;
				break;
			}
		}
		if (index == null) return null;
		byte[] newBytes = Arrays.copyOfRange(bytes, index, bytes.length);
		return new String(newBytes);
	}

    public static byte[] returnFF(int length) {
        if (length == 1) return new byte[]{BYTE_FF};
        if (length == 2) return WORD_FF;
        if (length == 3) return SWORD_FF;
        if (length == 4) return DWORD_FF;

        byte[] bytes = new byte[length];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) 0xFF;
        }
        return bytes;
    }

    public static String getZeroBytesString(int length) {

		return new String(getZeroBytes(length));
	}

	public static byte[] getZeroBytes(int length) {

		byte[] zeroBytes = new byte[length];
		for (int i = 0; i < length; i++) {
			zeroBytes[i] = 0x00;
		}

		return zeroBytes;
	}




	public  static  String getStringFromBytes(byte[] bytes) {

		return ByteUtils.isCorrectData(bytes) ? ByteUtils.getStringFromBytes(bytes) : null;
	}

	 public static void main(String[] args) {
			/*Integer a = 50;
	    	byte b = a.byteValue();
	    	Integer c = (b >> 4) & 0xf;
	    	Integer d = b & 0xf;
	    	System.out.println(c);
	    	System.out.println(d);*/

         byte b = (byte)1;

         byte[] bytes = byte2BitArray(WORD_FF[0]);

         for (int i = 0; i < WORD_FF.length; i++) {
             System.out.println(WORD_FF[i]);
         }


		 System.out.println("--------");

		 byte[] byteFromStr = writeString2Bytes("abc", null, 3);
		 String strFromBytes = new String(byteFromStr);
		 System.out.println(strFromBytes);

		 System.out.println("--------");
     }


	 
}
