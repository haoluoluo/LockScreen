package utils;

import java.io.IOException;

public class WindowOSUtils {
    public static void shutdown(){
        try {
            Runtime.getRuntime().exec("shutdown -s -t 00");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("==============");
    }

    public static void main(String[] args) {
        shutdown();
    }
}
