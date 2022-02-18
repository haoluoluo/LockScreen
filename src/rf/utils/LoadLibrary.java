package rf.utils;

import utils.FileUtils;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * @create 2022-02-18 11:31
 **/
public class LoadLibrary {
    public static void load(){
        String property = System.getProperty("sun.arch.data.model");
        URL res = null;
        if(Objects.equals(property, "64")){
            res = FileUtils.loadResource("win64/umf.dll");
        }else if(Objects.equals(property, "32")){
            res = FileUtils.loadResource("win32/umf.dll");
        }
        try {
            System.load(Paths.get(res.toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
