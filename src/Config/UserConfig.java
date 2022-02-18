package Config;

import utils.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author luoluo.hao
 * @create 2022-02-14 15:08
 **/
public class UserConfig {
    private static final Map<String, String> SUPER_USER = new HashMap<>();

    public static Map<String, String> getSuperUser() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(FileUtils.loadFile("passwd"), StandardCharsets.UTF_8));
        String s;
        SUPER_USER.clear();
        while ((s = in.readLine()) != null){
            String[] split = s.split(":");
            if(split.length != 2){
                continue;
            }
            SUPER_USER.put(split[0], split[1]);
        }
        return SUPER_USER;
    }
}
