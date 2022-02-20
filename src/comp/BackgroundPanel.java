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

        rightJPanel.add(passwdPanel, BackgroundCardType.CHANGE_PASSWORD);
        rightJPanel.add(icPanel, BackgroundCardType.IC_CARD_ACTIVE);


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
//        frame.setLocationRelativeTo(this);
//        this.setLayout(new BorderLayout());
//        JPanelEnh jPanelEnh = new JPanelEnh();
//
//        frame.setLocationRelativeTo(jPanelEnh);
//
//
//
//        jPanelEnh.setBorder(BorderFactory.createEmptyBorder(20,20,10,20));
//        jPanelEnh.setBackground(Color.BLUE);
//        jPanelEnh.setVisible(true);
//
//        JButton btn1=new JButton("我是普通按钮");    //创建JButton对象
//        JButton btn2=new JButton("我是带背景颜色按钮");
//        JButton btn3=new JButton("我是不可用按钮");
//        JButton btn4=new JButton("我是底部对齐按钮");
//
//        jPanelEnh.add(btn1);
//        jPanelEnh.add(btn2);
//        this.add(jPanelEnh);
//
//        SpringLayout.Constraints constraints = springLayout.getConstraints(jPanelEnh);
//        constraints.setX(Spring.constant(40));
//        constraints.setY(Spring.constant(40));
//        constraints.setConstraint(SpringLayout.SOUTH, springLayout.getConstraints(this).getConstraint(SpringLayout.SOUTH));

        this.setVisible(true);
    }
}
