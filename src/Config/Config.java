package Config;

import enums.UserStatus;
import utils.PropertiesUtils;

import java.util.Properties;

/**
 * @author luoluo.hao
 * @create 2022-02-14 15:50
 **/
public class Config {

    public static final String ASC_PASSWORD = "LmjrYcQdIbjH1S8q";

    public static final int CLICK_COUNT = 2;

    public static final String BACKGROUND_IMAGE = "minimalist.jpg";

    public static final String LOGO_IMAGE = "logo.png";

    public static final String INFORMATION_IMAGE = "info.png";
    public static final String H2_PROPERTIES = "H2.properties";

    public static final String SET_PROPERTIES = "set.properties";

    public static final Properties PROPERTIES = PropertiesUtils.load(SET_PROPERTIES, new Properties());

    public static final String BACKGROUND_IMAGE_PROPERTY = "backgroundImage";

    public static final String OUT_SHUTDOWN_PROPERTY = "outShutdown";

    public static final String PASSWORD_PROPERTIES = "password";

    private static UserStatus userStatus = UserStatus.NORMAL;

    synchronized
    public static UserStatus getUserStatus() {
        return userStatus;
    }

    synchronized
    public static void setUserStats(UserStatus userStatus){
        Config.userStatus = userStatus;
    }

    public static void main(String[] args) {
    }
}
