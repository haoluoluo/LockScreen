package comp;

import Config.Config;
import comp.base.JPanelEnh;
import enums.CardType;
import enums.UserStatus;
import org.quartz.SchedulerException;
import task.SchedulerMain;

import javax.swing.*;
import java.awt.*;

public class BackgroundTopPanel extends JPanelEnh {
    public BackgroundTopPanel(MainWindows mainWindows){
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton minButton = new JButton("最小化");

        minButton.addActionListener(e -> mainWindows.minimize() );

        this.add(minButton);

        JButton maxButton = new JButton("注销");

        maxButton.addActionListener(e -> {
            Config.setUserStats(UserStatus.NORMAL);
            mainWindows.changeCard(CardType.INFORMATION);
            mainWindows.setAlwaysOnTop(true);
        });
        this.add(maxButton);

        JButton exitButton = new JButton("关闭程序");

        exitButton.addActionListener(e -> {
            mainWindows.dispose();
            try {
                SchedulerMain.shutdown();
            } catch (SchedulerException ex) {
                ex.printStackTrace();
            }
        });
        this.add(exitButton);
    }
}
