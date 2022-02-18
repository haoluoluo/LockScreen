package comp;

import Config.Config;
import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author luoluo.hao
 * @create 2022-02-16 16:13
 **/
public class BackgroundImageJFrame extends JFrame {

    URL backUrl = FileUtils.loadResource(Config.BACKGROUND_IMAGE);
    JLabel background=new JLabel(new ImageIcon(backUrl));

    public BackgroundImageJFrame() {
        background.setBorder(BorderFactory.createLineBorder(Color.red));
        setVisible(true);

        setLayout(new BorderLayout());


        super.add(background);

    }

    @Override
    public Component add(Component comp) {
        return background.add(comp);
    }

    public static void main(String args[])
    {
        new BackgroundImageJFrame();
    }
}