package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;



/**
 * 对称加密（其实不是加密解密，准确讲应该叫“编码”与“解码”）
 * <p>
 * 算法列举》
 * 对称加密算法：DES算法，3DES算法，TDEA算法，Blowfish算法，RC5算法，IDEA算法，AES算法。
 * 非对称加密算法：RSA、Elgamal、背包算法、Rabin、D-H、ECC。
 * 经典的哈希算法：MD2、MD4、MD5 和 SHA-1（目的是将任意长输入通过算法变为固定长输出，且保证输入变化一点输出都不同，且不能反向解密）
 * 经典算法》
 * AES（对称），RSA（非对称），MD5,SHA-1（哈希）
 *
 * @Filename: com.septinary.common.util.Base64.java of the project [com.septinary.common]
 * @Type: Base64
 * @Desc: TODO
 * @Author: macbook[weide<weide001       @       gmail.com>]
 * @Created: 2016年3月24日上午12:16:25
 * <p>
 * Base64是网络上最常见的用于传输8Bit字节代码的编码方式之一，大家可以查看RFC2045~RFC2049，上面有
 * MIME的详细规范。Base64编码可用于在HTTP环境下传递较长的标识信息。例如，在Java
 * Persistence系统Hibernate
 * 中，就采用了Base64来将一个较长的唯一标识符(一般为128-bit的UUID)编码为一个字符串，用作HTTP表单和HTTP GET
 * URL 中的参数。在其他应用程序中，也常常需要把二进制数据编码为适合放在URL(包括隐藏表单域)中的形式。此时，采用
 * Base64编码具有不可读性，即所编码的数据不会被人用肉眼所直接看到，算是起到一个加密的作用。
 * Base64使用A--Z,a--z,0--9,+,/这64个字符，编码原理是将3个字节转换成4个字节( (3 X 8) = 24 =
 * (4 X 6) ) 先读入3个字节,每读一个字节,左移8位,再右移四次,每次6位,这样就有4个字节了，这样还不能保证得到的字符都是可
 * 见字符，为了达到此目的，Base64制定了一个编码表，进行统一的转换。码表的大小为2^6=64，这也是Base64名称 的由来。
 * 当剩下的字符数量不足3个字节时，则应使用0进行填充，相应的，输出字符则使用'='占位，因此编码后输出
 * 的文本末尾可能会出现1至2个'='。解码原理是将4个字节转换成3个字节.先读入4个6位(用或运算),每次左移6位,再
 * 右移3次,每次8位，这样就还原了。
 * Base64 编码表
 * Value	Char	 	Value	Char	 	Value	Char	 	Value	Char
 * 0		A			16		Q			32		g			48		w
 * 1		B			17		R			33		h			49		x
 * 2		C			18		S			34		i			50		y
 * 3		D			19		T			35		j			51		z
 * 4		E			20		U			36		k			52		0
 * 5		F			21		V			37		l			53		1
 * 6		G			22		W			38		m			54		2
 * 7		H			23		X			39		n			55		3
 * 8		I			24		Y			40		o			56		4
 * 9		J			25		Z			41		p			57		5
 * 10		K			26		a			42		q			58		6
 * 11		L			27		b			43		r			59		7
 * 12		M			28		c			44		s			60		8
 * 13		N			29		d			45		t			61		9
 * 14		O			30		e			46		u			62		+
 * 15		P			31		f			47		v			63		/
 * <p>
 * Base64是一种很常见的编码规范，其作用是将二进制序列转换为人类可读的ASCII字符序列，常用在需用通过文
 * 本协议（比如HTTP和SMTP）来传输二进制数据的情况下。Base64并不是一种用于安全领域的加密解密算法（这
 * 类算法有DES等），尽管我们有时也听到使用Base64来加密解密的说法，但这里所说的加密与解密实际是指编码
 * （encode）和解码（decode）的过程，其变换是非常简单的，仅仅能够避免信息被直接识别。
 */
public class Base64 implements ICryptable<String> {

    /**
     * 字符串(或者文件)Base64加密
     *
     * @param source  加密源字符串
     * @param options 加密选项		{"type":"file"}
     * @return String
     * @method encrypt()
     * @memo TODO
     * @see IEncryptable#encrypt(Object, HashMap)
     */
    @Override
    public String encrypt(String source, HashMap<Object, Object> options) {
        // TODO Auto-generated method stub
        // return null;

        String file = null;
        //加密选项
        if (null != options) {
            Object value = null;
            value = options.get("type");
            String type = null == value ? "" : value.toString();
            if ("file".equalsIgnoreCase(type)) {
                file = source;
            }
        }

        if (null == file) {
            return Base64.encode(source);
        } else {
            try {
                Base64.encode(new File(file));
            } catch (IOException e) {
                return null;
            }
            return file;
        }
    }

    /**
     * 字符串(或者文件)Base64解密
     *
     * @param target  解密目标符串
     * @param options 解密选项		{"type":"file"}
     * @return String
     * @method encrypt()
     * @memo TODO
     * @see IDecryptable#decrypt(Object, HashMap)
     */
    @Override
    public String decrypt(String target, HashMap<Object, Object> options) {
        // TODO Auto-generated method stub
        // return null;

        String file = null;
        //解密选项
        if (null != options) {
            Object value = null;
            value = options.get("type");
            String type = null == value ? "" : value.toString();
            if ("file".equalsIgnoreCase(type)) {
                file = target;
            }
        }

        if (null == file) {
            return Base64.decode(target);
        } else {
            try {
                Base64.decode(new File(file));
            } catch (IOException e) {
                return null;
            }
            return file;
        }
    }

    public String encrypt(byte[] source) {
        return new String(encode(source));
    }

    public byte[] decrypt(String target) {
        return decode(target.toCharArray());
    }

    public File encrypt(File source) {
        try {
            encode(source);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
        return source;
    }

    public File decrypt(File target) {
        try {
            decode(target);
        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }

        return target;
    }

    //---------------------------------------------------------
    private static String encode(String data) {
        return new String(encode(data.getBytes()));
    }

    private static String decode(String data) {
        return new String(decode(data.toCharArray()));
    }

    private static char[] encode(byte[] data) {
        char[] out = new char[((data.length + 2) / 3) * 4];
        for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
            boolean quad = false;
            boolean trip = false;

            int val = (0xFF & (int) data[i]);
            val <<= 8;
            if ((i + 1) < data.length) {
                val |= (0xFF & (int) data[i + 1]);
                trip = true;
            }
            val <<= 8;
            if ((i + 2) < data.length) {
                val |= (0xFF & (int) data[i + 2]);
                quad = true;
            }
            out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
            val >>= 6;
            out[index + 1] = alphabet[val & 0x3F];
            val >>= 6;
            out[index + 0] = alphabet[val & 0x3F];
        }
        return out;
    }

    private static byte[] decode(char[] data) {

        int tempLen = data.length;
        for (int ix = 0; ix < data.length; ix++) {
            if ((data[ix] > 255) || codes[data[ix]] < 0) {
                --tempLen; // ignore non-valid chars and padding
            }
        }
        // calculate required length:
        // -- 3 bytes for every 4 valid base64 chars
        // -- plus 2 bytes if there are 3 extra base64 chars,
        // or plus 1 byte if there are 2 extra.

        int len = (tempLen / 4) * 3;
        if ((tempLen % 4) == 3) {
            len += 2;
        }
        if ((tempLen % 4) == 2) {
            len += 1;

        }
        byte[] out = new byte[len];

        int shift = 0; // # of excess bits stored in accum
        int accum = 0; // excess bits
        int index = 0;

        // we now go through the entire array (NOT using the 'tempLen' value)
        for (int ix = 0; ix < data.length; ix++) {
            int value = (data[ix] > 255) ? -1 : codes[data[ix]];

            if (value >= 0) { // skip over non-code
                accum <<= 6; // bits shift up by 6 each time thru
                shift += 6; // loop, with new bits being put in
                accum |= value; // at the bottom.
                if (shift >= 8) { // whenever there are 8 or more shifted in,
                    shift -= 8; // write them out (from the top, leaving any
                    out[index++] = // excess at the bottom for next iteration.
                            (byte) ((accum >> shift) & 0xff);
                }
            }
        }

        // if there is STILL something wrong we just have to throw up now!
        if (index != out.length) {
            throw new Error("Miscalculated data length (wrote " + index + " instead of " + out.length + ")");
        }

        return out;
    }

    private static void encode(File file) throws IOException {
        if (!file.exists()) {
            return;
        } else {
            byte[] decoded = readBytes(file);
            char[] encoded = encode(decoded);
            writeChars(file, encoded);
        }
        file = null;
    }

    private static void decode(File file) throws IOException {
        if (!file.exists()) {
            return;
        } else {
            char[] encoded = readChars(file);
            byte[] decoded = decode(encoded);
            writeBytes(file, decoded);
        }
        file = null;
    }

    //
    // code characters for values 0..63
    //
    private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
    //
    // lookup table for converting base64 characters to value in range 0..63
    //
    private static byte[] codes = new byte[256];

    static {
        for (int i = 0; i < 256; i++) {
            codes[i] = -1;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            codes[i] = (byte) (i - 'A');
        }

        for (int i = 'a'; i <= 'z'; i++) {
            codes[i] = (byte) (26 + i - 'a');
        }
        for (int i = '0'; i <= '9'; i++) {
            codes[i] = (byte) (52 + i - '0');
        }
        codes['+'] = 62;
        codes['/'] = 63;
    }

    private static byte[] readBytes(File file) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] b = null;
        InputStream fis = null;
        InputStream is = null;
        try {
            fis = new FileInputStream(file);
            is = new BufferedInputStream(fis);
            int count = 0;
            byte[] buf = new byte[16384];
            while ((count = is.read(buf)) != -1) {
                if (count > 0) {
                    baos.write(buf, 0, count);
                }
            }
            b = baos.toByteArray();

        } finally {
            try {
                if (fis != null)
                    fis.close();
                if (is != null)
                    is.close();
                if (baos != null)
                    baos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return b;
    }

    private static char[] readChars(File file) throws IOException {
        CharArrayWriter caw = new CharArrayWriter();
        Reader fr = null;
        Reader in = null;
        try {
            fr = new FileReader(file);
            in = new BufferedReader(fr);
            int count = 0;
            char[] buf = new char[16384];
            while ((count = in.read(buf)) != -1) {
                if (count > 0) {
                    caw.write(buf, 0, count);
                }
            }

        } finally {
            try {
                if (caw != null)
                    caw.close();
                if (in != null)
                    in.close();
                if (fr != null)
                    fr.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return caw.toCharArray();
    }

    private static void writeBytes(File file, byte[] data) throws IOException {
        OutputStream fos = null;
        OutputStream os = null;
        try {
            fos = new FileOutputStream(file);
            os = new BufferedOutputStream(fos);
            os.write(data);

        } finally {
            try {
                if (os != null)
                    os.close();
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }

    private static void writeChars(File file, char[] data) throws IOException {
        Writer fos = null;
        Writer os = null;
        try {
            fos = new FileWriter(file);
            os = new BufferedWriter(fos);
            os.write(data);

        } finally {
            try {
                if (os != null)
                    os.close();
                if (fos != null)
                    fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 文件转Base64字符串
     *
     * @param filename  文件名称（全路径名称）
     * @return String
     */
    public String FileToBase64(String filename) {
        String base64 = null;
        InputStream in = null;

        try {
            File file = new File(filename);
            in = new FileInputStream(file);
            byte[] bytes = new byte[in.available()];
            base64 = java.util.Base64.getEncoder().encodeToString(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return base64;
    }

    /**
     * Base64字符串存为文件
     *
     * @param base64  源Base64字符串
     * @param filename  文件名称（全路径名称）
     * @return String
     */
    public static void Base64ToFile(String base64, String filename) {
        File file = null;

        //创建文件目录
        String filePath = FileUtils.BasePath(filename);
        File  dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()) {
            dir.mkdirs();
        }

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            byte[] bytes = java.util.Base64.getDecoder().decode(base64);
            file = new File(filename);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
