package Config;

import org.apache.commons.lang3.StringUtils;
import utils.AES;
import utils.PropertiesUtils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author luoluo.hao
 * @create 2022-02-14 15:08
 **/
public class UserConfig {

    public static String getSuperUserPassword() {

        String password = String.valueOf(Config.PROPERTIES.get(Config.PASSWORD_PROPERTIES));
        if(StringUtils.isEmpty(password)){
            password = encrypt("admin");
        }
        return password;
    }

    public static Boolean checkPassword(String password){
        String passwd = encrypt(password);
        String realPd = getSuperUserPassword();
        return Objects.equals(passwd, realPd);
    }

    public static void setPassword(String password) {
        String passwd = encrypt(password);
        Config.PROPERTIES.setProperty(Config.PASSWORD_PROPERTIES, passwd);
        PropertiesUtils.store(Config.SET_PROPERTIES, Config.PROPERTIES);
    }
    public static String encrypt(String password){
        byte[] encrypt = new AES().encrypt(password.getBytes(StandardCharsets.UTF_8), Config.ASC_PASSWORD.getBytes(StandardCharsets.UTF_8), null);
        return new String(encrypt, StandardCharsets.UTF_8);
    }
}
