import utils.AltTabStopper;
import utils.FileUtils;
import utils.ThreadPoolUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

/**
 * @create 2022-02-15 10:14
 **/
public class test {
    public static void main(String[] args) throws IOException, URISyntaxException {
//        String arch = System.getProperty("sun.arch.data.model");
//        System.out.println(arch+"-bit");
//        File file = new File("");
//        System.out.println(file.exists());
//        System.out.println(file.getAbsolutePath());
//        System.out.println(System.getProperty("user.dir"));
        URL res = FileUtils.loadResource("win64/umf.dll");
        File file = Paths.get(res.toURI()).toFile();
        String absolutePath = file.getAbsolutePath();
        System.out.println(absolutePath);
//        JFrame f=new JFrame();
//        Toolkit toolkit=f.getToolkit();
//        Image topicon=toolkit.getImage("你的图片地址");
//        f.setIconImage(topicon);
//        URL resource = FileUtils.loadResource("logo.png");
//        System.out.println(resource.toString());

////        ThreadPoolUtils.executor(new AltTabStopper(null));
//        File file = new File("Lockscreen.exe");
//        System.out.println(file.getAbsolutePath());
////        Desktop desktop = Desktop.getDesktop();
////
////        desktop.open(file);
////        Runtime.getRuntime().exec("./Lockscreen.exe");
////        try {
////            Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        Robot robot = null;
//        try {
//            robot = new Robot();
//            robot.delay(1000);
////            robot.keyPress(KeyEvent.VK_ENTER);
////            robot.keyRelease(KeyEvent.VK_ENTER);
////            robot.keyPress(KeyEvent.VK_CONTROL);
////            robot.keyPress(KeyEvent.VK_L);
////            robot.keyRelease(KeyEvent.VK_L);
////            robot.keyRelease(KeyEvent.VK_CONTROL);
//
//            robot.delay(1000);
//            robot.keyPress(KeyEvent.VK_1);
//            robot.keyRelease(KeyEvent.VK_1);
//            robot.keyPress(KeyEvent.VK_2);
//            robot.keyRelease(KeyEvent.VK_2);
//            robot.keyPress(KeyEvent.VK_3);
//            robot.keyRelease(KeyEvent.VK_3);
//            robot.keyPress(KeyEvent.VK_4);
//            robot.keyRelease(KeyEvent.VK_4);
//            robot.keyPress(KeyEvent.VK_5);
//            robot.keyRelease(KeyEvent.VK_5);
//            robot.keyPress(KeyEvent.VK_6);
//            robot.keyRelease(KeyEvent.VK_6);
//            robot.keyPress(KeyEvent.VK_ENTER);
//            robot.keyRelease(KeyEvent.VK_ENTER);
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }

    }
}
