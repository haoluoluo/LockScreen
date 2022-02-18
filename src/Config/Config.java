package Config;

import enums.UserStatus;

/**
 * @author luoluo.hao
 * @create 2022-02-14 15:50
 **/
public class Config {

    public static final String ASC_PASSWORD = "LmjrYcQdIbjH1S8q";

    public static final String PASSWORD_FILE = "passwd";

    public static final int CLICK_COUNT = 2;

    public static final String BACKGROUND_IMAGE = "minimalist.jpg";

    public static final String LOGO_IMAGE = "logo.png";

    public static final String INFORMATION_IMAGE = "info.png";

    private static UserStatus userStatus = UserStatus.NORMAL;

    synchronized
    public static UserStatus getUserStatus() {
        return userStatus;
    }

    synchronized
    public static void setUserStats(UserStatus userStatus){
        Config.userStatus = userStatus;
    }
}
