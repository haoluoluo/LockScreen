package utils;

/**
 * 字符串操作
 *
 * @Filename: com.septinary.common.StringUtil.java of the project [com.septinary.common]
 * @Type: StringUtil
 * @Desc: TODO
 * @Author: macbook[weide<weide001@gmail.com>]
 * @Created: 2016-03-01 19:15:00
 */
abstract public class StringUtil {

    /**
     * 是否无效字符串，即是否为 NULL 或者 空白串
     *
     * @param string
     * @return boolean
     * @method StringUtil: Invalid()
     * @memo TODO
     */
    static public boolean Invalid(String string) {
        return (null == string || "".equals(string.trim()));
    }

    public static String bytesToHexString(byte[] src, int length){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * Convert hex string to byte[]
     * @param hexString the hex string
     * @return byte[]
     */
//    public static byte[] hexStringToBytes(String hexString) {
//        if (hexString == null || hexString.equals("")) {
//            return null;
//        }
//        hexString = hexString.toUpperCase();
//        int length = hexString.length() / 2;
//        char[] hexChars = hexString.toCharArray();
//        byte[] d = new byte[length];
//        for (int i = 0; i < length; i++) {
//            int pos = i * 2;
//            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
//        }
//        return d;
//    }
}
