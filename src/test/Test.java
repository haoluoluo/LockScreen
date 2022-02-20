package test;

import Config.Config;
import Config.UserConfig;
import com.sun.jdi.Field;
import utils.AES;

import java.io.File;
import java.nio.charset.StandardCharsets;

public class Test {
    public static void main(String[] args) {
        String classPath = Test.class.getResource("/").getPath();
        System.out.println("项目路径：" + classPath);
//        System.out.println(UserConfig.encrypt("123456"));
//        UserConfig.setPassword("123456");
//        System.out.println(UserConfig.getSuperUserPassword());
//        System.out.println(UserConfig.getSuperUserPassword());
//        String s = "123456";
//        byte[] encrypt = new AES().encrypt(s.getBytes(StandardCharsets.UTF_8), Config.ASC_PASSWORD.getBytes(StandardCharsets.UTF_8), null);
//        System.out.println(new String(encrypt));
//        byte[] decrypt = new AES().decrypt(encrypt, Config.ASC_PASSWORD.getBytes(StandardCharsets.UTF_8), null);
//        System.out.println(new String(decrypt, StandardCharsets.UTF_8));
    }
}
