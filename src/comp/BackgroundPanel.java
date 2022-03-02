package comp;


import comp.base.JPanelEnh;
import enums.BackgroundCardType;

import javax.swing.*;
import java.awt.*;

/**
 * @author luoluo.hao
 * @create 2022-02-18 17:43
 **/
public class BackgroundPanel extends JPanelEnh {

    public BackgroundPanel(MainWindows frame){

        this.setLayout(new BorderLayout());


        BackgroundTopPanel topPanel = new BackgroundTopPanel(frame);

        JPanelEnh rightJPanel = new JPanelEnh();

        LeftBackgroundPanel leftJPanel = new LeftBackgroundPanel(rightJPanel);

        rightJPanel.setTransparent();


        rightJPanel.setLayout(new CardLayout());

        PasswdPanel passwdPanel = new PasswdPanel();
        ICPanel icPanel = new ICPanel();

        CardInfoPanel cardInfoPanel = new CardInfoPanel();

        SwipeCardPanel swipeCardPanel = new SwipeCardPanel();

        AppearancePanel appearancePanel = new AppearancePanel();
        rightJPanel.add(new HelloPanel(), "welcome");
        rightJPanel.add(cardInfoPanel, BackgroundCardType.IC_CARD_INFO);
        rightJPanel.add(swipeCardPanel, BackgroundCardType.IC_HISTORY);
        rightJPanel.add(icPanel, BackgroundCardType.IC_CARD_ACTIVE);
        rightJPanel.add(appearancePanel, BackgroundCardType.APPEARANCE);
        rightJPanel.add(passwdPanel, BackgroundCardType.CHANGE_PASSWORD);


        rightJPanel.setMinimumSize(new Dimension(60,60));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                leftJPanel, rightJPanel);

        splitPane.setBackground(null);
        splitPane.setOpaque(false);

        splitPane.setResizeWeight(0.2);
        splitPane.setOneTouchExpandable(true);
        splitPane.setContinuousLayout(true);

        this.add(topPanel, BorderLayout.NORTH);
        this.add(splitPane, BorderLayout.CENTER);

        this.setVisible(true);
    }
}
