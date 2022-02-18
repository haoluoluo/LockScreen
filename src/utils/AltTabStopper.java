package utils;


import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * @author luoluo.hao
 * @create 2022-02-15 9:03
 **/
public class AltTabStopper implements Runnable {
    private boolean working = true;
    private JFrame frame;
    public AltTabStopper(JFrame frame) {
        this.frame = frame;
    }

    public void stop() {
        working = false;
    }
    public void start(){
        working = true;
    }
    public static AltTabStopper create(JFrame frame) {
        AltTabStopper stopper = new AltTabStopper(frame);
        new Thread(stopper, "Alt-Tab Stopper").start();
        return stopper;
    }
    @Override
    public void run() {
        try {
            Robot robot = new Robot();
            while (true) {
//                robot.keyPress(KeyEvent.VK_WINDOWS);
//                robot.keyRelease(KeyEvent.VK_WINDOWS);
//                robot.keyRelease(KeyEvent.VK_ALT);
                while (working){
                    System.out.println("1111111111");
                    robot.keyRelease(KeyEvent.VK_TAB);
                }
//                Runtime.getRuntime().exec("RunDll32.exe user32.dll,LockWorkStation");
//                robot.keyPress(KeyEvent.VK_ENTER);
//                robot.keyRelease(KeyEvent.VK_ENTER);
//                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
//                clipboard.setContents(new StringSelection("xishiduo"), null);
                //按下然后释放table键
//                robot.keyPress(KeyEvent.VK_TAB);
//                robot.keyRelease(KeyEvent.VK_TAB);
//                robot.delay(100);
//                clipboard.setContents(new StringSelection("密码"), null);
//                robot.delay(100);
//                robot.keyPress(KeyEvent.VK_CONTROL);
//                robot.keyPress(KeyEvent.VK_V);
//                robot.delay(100);
//                robot.keyRelease(KeyEvent.VK_V);
//                robot.keyRelease(KeyEvent.VK_CONTROL);
//                robot.delay(100);
//                //单机回车 随后释放回车键  如果回车登录没反应移动鼠标点击
//                robot.keyPress(KeyEvent.VK_ENTER);
//                robot.keyRelease(KeyEvent.VK_ENTER);
//                frame.requestFocus();
                try {
                    Thread.sleep(10);
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

