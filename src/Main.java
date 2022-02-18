

import javax.swing.*;
;

/**
 * @author luoluo.hao
 * @create 2022-02-14 9:54
 **/
public class Main {



    public static void main(String[] args) {
//        ThreadPoolUtils.executor(Main.altTabStopper);
        // 显示应用 GUI
//        SwingUtilities.invokeLater(MyFrame::createAndShowGUI);
        SwingUtilities.invokeLater(MainWindows::new);
    }
}
