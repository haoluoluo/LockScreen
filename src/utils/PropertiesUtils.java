package utils;

import java.io.*;
import java.util.Properties;

public class PropertiesUtils {
    public static void store(String filePath, Properties properties){
        try {
            File file = new File(filePath);
            properties.store(new PrintStream(file), "utf-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Properties load(String filePath, Properties properties) {
        File file = new File(filePath);
        try {
            properties.load(new FileInputStream(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
