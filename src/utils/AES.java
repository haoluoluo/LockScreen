package utils;


import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 对称加密算法
 * 对称加密算法用来对敏感数据等信息进行加密
 * AES（Advanced Encryption Standard）：高级加密标准，是下一代的加密算法标准，速度快，安全级别高；
 *
 * @Filename: com.septinary.common.util.AES.java of the project [com.septinary.common]
 * @Type: AES
 * @Desc: TODO
 * @Author: macbook[weide<weide001               @               gmail.com>]
 * @Created: 2016年3月22日下午5:45:00
 * <p>
 * 消息摘要只能检查消息的完整性，但是单向的，对明文消息并不能加密，要加密明文的消息的话，就要使用其他的算
 * 法，要确保机密性，我们需要使用私钥密码术来交换私有消息。这种最好理解，使用对称算法。比如：A用一个密钥对一个
 * 文件加密，而B读取这个文件的话，则需要和A一样的密钥，双方共享一个私钥（而在web环境下，私钥在传递时容易被侦
 * 听）：使用私钥加密的话，首先需要一个密钥，可用javax.crypto.KeyGenerator产生一个密钥 (java.security.Key),
 * 然后传递给一个加密工具(javax.crypto.Cipher),该工具再使用相应的算法来进行加密，主要对称算法有：DES（实
 * 际密钥只用到56位），AES（支持三种密钥长度：128、192、256位），通常首先128位，其他的还有DESede等。
 * <p>
 * 私钥加密需要一个共享的密钥，那么如何传递密钥呢？web环境下，直接传递的话很容易被侦听到，幸好有了公钥加
 * 密的出现。公钥加密也叫不对称加密，不对称算法使用一对密钥对，一个公钥，一个私钥，使用公钥加密的数据，只有私钥
 * 能解开（可用于加密）；同时，使用私钥加密的数据，只有公钥能解开（签名）。但是速度很慢（比私钥加密慢100到1000
 * 倍），公钥的主要算法有RSA，还包括Blowfish,Diffie-Helman等。
 * <p>
 * ----------------------------------------------------------------------------------------
 * <p>
 * 密码学中的高级加密标准（Advanced Encryption Standard，AES），又称Rijndael加密法，是美国联邦政
 * 府采用的一种区块加密标准。这个标准用来替代原先的DES，已经被多方分析且广为全世界所使用。经过五年的甄选流程，高
 * 级加密标准由美国国家标准与技术研究院 （NIST）于2001年11月26日发布于FIPS PUB 197，并在2002年5月26日成
 * 为有效的标准。2006年，高级加密标准已然成为对称密钥加密中最流行的算法之一。该算法为比利时密码学家Joan Daemen
 * 和Vincent Rijmen所设计，结合两位作者的名字，以Rijndael之命名之，投稿高级加密标准的甄选流程。（Rijdael
 * 的发音近于 "Rhinedoll"。）
 * AES是美国国家标准技术研究所NIST旨在取代DES的21世纪的加密标准。
 * AES的基本要求是，采用对称分组密码体制，密钥长度的最少支持为128、192、256，分组长度128位，算法应易于
 * 各种硬件和软件实现。1998年NIST开始AES第一轮分析、测试和征集，共产生了15个候选算法。1999年3月完成了第二轮
 * AES2的分析、测试。2000年10月2日美国政府正式宣布选中比利时密码学家Joan Daemen 和 Vincent Rijmen 提
 * 出的一种密码算法RIJNDAEL 作为 AES. 　　在应用方面，尽管DES在安全上是脆弱的，但由于快速DES芯片的大量生产，
 * 使得DES仍能暂时继续使用，为提高安全强度，通常使用独立密钥的三级DES。但是DES迟早要被AES代替。流密码体制较之
 * 分组密码在理论上成熟且安全，但未被列入下一代加密标准。
 * AES加密数据块和密钥长度可以是128比特、192比特、256比特中的任意一个。
 * AES加密有很多轮的重复和变换。大致步骤如下：
 * 1、密钥扩展（KeyExpansion），
 * 2、初始轮（Initial Round），
 * 3、重复轮（Rounds），每一轮又包括：SubBytes、ShiftRows、MixColumns、AddRoundKey，
 * 4、最终轮（Final Round），最终轮没有MixColumns。
 * JDK对DESede算法的支持
 * 密钥长度：128位
 * 工作模式：ECB/CBC/PCBC/CTR/CTS/CFB/CFB8 to CFB128/OFB/OBF8 to OFB128
 * 填充方式：Nopadding/PKCS5Padding/ISO10126Padding/
 */
public class AES implements ICryptable<String> {

    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    private static Key makekey(byte[] key) {
        //生成密钥
        try {
            return new SecretKeySpec(key, KEY_ALGORITHM);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
            return null;
        }
    }

    private static byte[] encrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(StringUtil.Invalid(cipherAlgorithm) ? DEFAULT_CIPHER_ALGORITHM : cipherAlgorithm);
        //使用密钥初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, Key key, String cipherAlgorithm) throws Exception {
        //实例化
        Cipher cipher = Cipher.getInstance(StringUtil.Invalid(cipherAlgorithm) ? DEFAULT_CIPHER_ALGORITHM : cipherAlgorithm);
        //使用密钥初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, key);
        //执行操作
        return cipher.doFinal(data);
    }

    /**
     * 制作加密密钥
     *
     * @return byte[]
     * @method AES: MakeSecretKey()
     * @memo TODO
     */
    public static byte[] MakeSecretKey() {
        //返回生成指定算法的密钥的密钥生成器（KeyGenerator）对象
        KeyGenerator kg = null;
        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

        //初始化此密钥生成器，使其具有确定的密钥大小
        kg.init(128); //AES要求：密钥长度为 128 位

        //生成一个密钥
        SecretKey secretKey = kg.generateKey();

        //返回密钥的字节数组
        return secretKey.getEncoded();
    }

    /**
     * 字符串加密
     *
     * @param source  加密源字符串
     * @param options 加密选项		{"key": 密钥{byte[]}, "algorithm": "{算法名称}"}
     * @return String base64格式的密文
     * @method encrypt()
     * @memo TODO
     * @see IEncryptable#encrypt(Object, HashMap)
     */
    @Override
    public String encrypt(String source, HashMap<Object, Object> options) {
        // TODO Auto-generated method stub
        // return null;

        byte[] sk = null;
        String algorithm = null;
        //加密选项
        if (null != options) {
            Object value = null;
            value = options.get("key");
            value = (sk = null == value ? MakeSecretKey() : (byte[]) value);
            options.put("key", value);
            value = options.get("algorithm");
            algorithm = null == value ? null : value.toString();
        }
        Key key = makekey(sk);

        byte[] encryptData = null;
        try {
            encryptData = encrypt(source.getBytes(), key, algorithm);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return new String(new Base64().encrypt(encryptData)); //输出之前，将byte[]流密文转换为base64密文 ...
    }

    /**
     * 字符串解密
     *
     * @param target  解密目标符串 base64格式的密文
     * @param options 解密选项		{"key": 密钥{byte[]}, "algorithm": "{算法名称}"}
     * @return String
     * @method encrypt()
     * @memo TODO
     * @see IDecryptable#decrypt(Object, HashMap)
     */
    @Override
    public String decrypt(String target, HashMap<Object, Object> options) {
        // TODO Auto-generated method stub
        // return null;

        byte[] sk = null;
        String algorithm = null;
        //加密选项
        if (null != options) {
            Object value = null;
            value = options.get("key");
            value = (sk = null == value ? MakeSecretKey() : (byte[]) value);
            options.put("key", value);
            value = options.get("algorithm");
            algorithm = null == value ? null : value.toString();
        }
        Key key = makekey(sk);

        byte[] decryptData = null;
        try {
            decryptData = decrypt(new Base64().decrypt(target), key, algorithm); //解密之前，将base64格式的密文转换为byte[]流密文 ...
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }

        return new String(decryptData);
    }

    public byte[] encrypt(byte[] source, byte[] key, String algorithm) {
        try {
            return encrypt(source, makekey(key), algorithm);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public byte[] decrypt(byte[] target, byte[] key, String algorithm) {
        try {
            return decrypt(target, makekey(key), algorithm);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public byte[] encrypt(byte[] source, byte[] key) {
        return this.encrypt(source, key, null);
    }

    public byte[] decrypt(byte[] target, byte[] key) {
        return this.decrypt(target, key, null);
    }

    public byte[] encrypt(byte[] source, String key) {
        return this.encrypt(source, key.getBytes());
    }

    public byte[] decrypt(byte[] target, String key) {
        return this.decrypt(target, key.getBytes());
    }

}
