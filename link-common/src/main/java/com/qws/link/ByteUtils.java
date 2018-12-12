/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *
 */
package com.qws.link;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.Date;

/**
 * @author <a href="http://mina.apache.org">Apache MINA Project</a>
 * @since MINA 2.0.0-M3
 */
public class ByteUtils {
    private ByteUtils() {
    }

    public static String asHex(byte[] bytes) {
        return asHex(bytes, null);
    }

    public static String asHex(byte b) {
        String code = Integer.toHexString(b & 0xFF);
        if ((b & 0xFF) < 16) {
            return "0" + code;
        } else {
            return code;
        }
    }


    public static String asHex(Byte[] bytes) {
        byte[] val = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            val[i] = bytes[i].byteValue();
        }
        return asHex(val, null);
    }

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

    public static byte[] byte2BitArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 0; i < 8; i++) {
            array[i] = (byte) (b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }


    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            stringBuilder.append(" 0x");
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }

    /**
     * 16进制的String字符串去0x位以后转成byte数组
     * */
    public static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return new byte[0];
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

    public static byte[] intToByteArray(final int integer, int length) {
        byte[] byteArray = new byte[length];
        for (int i = 0; i < length; i++) {
            byteArray[i] = (byte) (integer >>> ((length - 1 - i) * 8));
        }
        return byteArray;
    }

    public static byte[] intToByteArray(final int integer) {
        int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
        byte[] byteArray = new byte[4];

        for (int n = 0; n < byteNum; n++)
            byteArray[3 - n] = (byte) (integer >>> (n * 8));

        return (byteArray);
    }

    public static byte[] intToByteArray2(final int integer) {
        int byteNum = (40 - Integer.numberOfLeadingZeros(integer < 0 ? ~integer : integer)) / 8;
        byte[] byteArray = new byte[4];

        for (int n = 0; n < byteNum; n++)
            byteArray[3 - n] = (byte) (integer >>> (n * 8));
        return new byte[]{byteArray[2], byteArray[3]};
    }

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

    public static int byteArrayToInt(byte a, byte b) {
        return ((0xff & a) << 8) + (0xff & b);
    }

    public static byte[] convertDatetime2ByteArr(Date date) {
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


    public static Date convertByteArrtDatetime(byte[] data) throws ParseException {
        String dateStr = "20" + data[0] + "-" + data[1] + "-" + data[2] + " " + (data[3] == 0x00 ? "00" : data[3]) + ":"
                + (data[4] == 0x00 ? "00" : data[4]) + ":" + (data[5] == 0x00 ? "00" : data[5]);
        return DateTimeUtils.parseDate(dateStr, "yyyy-MM-dd HH:mm:ss");
    }


    public static byte[] getBytes(char[] chars) {
        byte[] bytes = new byte[chars.length];
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            bytes[i] = (byte) c;
        }
        return bytes;
    }

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
            } else if (obj instanceof Integer) {
                result[pos] = ((Integer) obj).byteValue();
                pos += 1;
            } else {
                result[pos] = (byte) obj;
                pos += 1;
            }
        }
        return result;
    }

    /**
     * 参数只能为 byte或者byte数组 不可以用Byte对象或者Byte对象数组
     */
    public static <T> T[] addAll(Class<T> clazz, Object... objs) {
        int length = 0;
        for (Object obj : objs) {
            if (obj.getClass().isArray()) {
                T[] objArr = (T[]) obj;
                length += objArr.length;
            } else {
                length += 1;
            }
        }
        T[] result = (T[]) Array.newInstance(clazz, length);
        int pos = 0;
        for (Object obj : objs) {
            if (obj.getClass().isArray()) {
                T[] objArr = (T[]) obj;
                System.arraycopy(objArr, 0, result, pos, objArr.length);
                pos += objArr.length;
            } else {
                result[length] = (T) obj;
                pos += 1;
            }
        }
        return result;
    }

    /**
     * @param a    需要转化的byte
     * @param der  除数
     * @param diff 差值
     * @return
     */
    public static Double toDouble(byte a, int der, int diff) {
        int temp = 0xff & a;
        return intToDouble(temp, der, diff);
    }

    /**
     * @param a    需要转化的byte
     * @param b    需要转化的byte
     * @param der  除数
     * @param diff 差值
     * @return
     */
    public static Double toDouble(byte a, byte b, int der, int diff) {
        int temp = ByteUtils.byteArrayToInt(new byte[]{0x00, 0x00, a, b}, 0);
        return intToDouble(temp, der, diff);
    }

    public static Double toDouble(byte a, byte b, byte c, byte d, int der, int diff) {
        int temp = ByteUtils.byteArrayToInt(new byte[]{a, b, c, d}, 0);
        return intToDouble(temp, der, diff);
    }

    public static Double intToDouble(int temp, int der, int diff) {
        return ((double) (temp - diff * der)) / der;
    }


    /**
     * @param d        需要转化的double
     * @param multiply 乘数
     * @param add      加值
     * @return
     */
    public static int DoubleToInt(double d, int multiply, int add) {
        return (int) (d * multiply) + add * multiply;
    }
}