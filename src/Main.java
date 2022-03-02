

import Config.Config;
import comp.MainWindows;
import database.Database;
import enums.BackgroundCardType;
import enums.CardType;
import enums.UserStatus;
import utils.FileUtils;

import javax.swing.*;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

/**
 * @author luoluo.hao
 * @create 2022-02-14 9:54
 **/
public class Main {



    public static void main(String[] args) {
        List<String[]> strings = Database.queryICCardHistoryTemp();
        if(strings.size()>0){
            Config.setUserStats(UserStatus.USER_LOGIN);
        }else {
            Config.setUserStats(UserStatus.NORMAL);
        }
        // 显示应用 GUI
        SwingUtilities.invokeLater(MainWindows::new);
    }
}
