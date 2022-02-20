package Config;

import utils.AES;
import utils.FileUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author luoluo.hao
 * @create 2022-02-14 15:08
 **/
public class UserConfig {

    public static String getSuperUserPassword() {
        return FileUtils.read(Config.PASSWORD_FILE);
    }

    public static Boolean checkPassword(String password){
        String passwd = encrypt(password);
        String realPd = getSuperUserPassword();
        return Objects.equals(passwd, realPd);
    }

    public static void setPassword(String password) {
        String passwd = encrypt(password);
        FileUtils.write(Config.PASSWORD_FILE, passwd);
    }
    public static String encrypt(String password){
        byte[] encrypt = new AES().encrypt(password.getBytes(StandardCharsets.UTF_8), Config.ASC_PASSWORD.getBytes(StandardCharsets.UTF_8), null);
        return new String(encrypt, StandardCharsets.UTF_8);
    }
}
