package comp;

import Config.Config;
import comp.base.JPanelEnh;
import enums.CardType;
import enums.UserStatus;

import javax.swing.*;
import java.awt.*;

public class BackgroundTopPanel extends JPanelEnh {
    public BackgroundTopPanel(MainWindows mainWindows){
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton minButton = new JButton("最小化");

        minButton.addActionListener(e -> {
            mainWindows.minimize();
        });

        this.add(minButton);

        JButton maxButton = new JButton("注销");

        maxButton.addActionListener(e -> {
            Config.setUserStats(UserStatus.NORMAL);
            mainWindows.changeCard(CardType.INFORMATION);
        });
        this.add(maxButton);
    }
}
