package comp;

import comp.base.JLabelEnh;
import comp.base.JPanelEnh;

import javax.swing.*;
import java.awt.*;


public class HelloPanel extends JPanelEnh {
    public HelloPanel(){
        //设置透明
        this.setTransparent();

        this.setLayout(new BorderLayout());

        JLabelEnh jLabelEnh = new JLabelEnh("欢迎进入后台程序");

        jLabelEnh.setFont(new Font("",Font.BOLD, 80));
        jLabelEnh.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(jLabelEnh, BorderLayout.CENTER);


        this.setVisible(true);

    }

}
