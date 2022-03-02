package utils;

/**
 * 字符串操作
 */
abstract public class StringUtil {

    /**
     * 是否无效字符串，即是否为 NULL 或者 空白串
     *
     * @return boolean
     * @method StringUtil: Invalid()
     */
    static public boolean Invalid(String string) {
        return (null == string || "".equals(string.trim()));
    }


    public static String bytesToHexString(byte[] src, int length){
        StringBuilder stringBuilder = new StringBuilder();
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
}
