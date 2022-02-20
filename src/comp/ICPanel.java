package comp;


import Config.H2Config;
import Config.UserConfig;
import comp.base.JLabelEnh;
import comp.base.JPanelEnh;
import utils.ICUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * @author luoluo.hao
 * @create 2022-02-19 19:23
 **/
public class ICPanel extends JPanelEnh {
    public ICPanel(){

        this.setTransparent();
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        JLabelEnh icIDLabel = new JLabelEnh("卡号: ");

        JTextField icIDField = new JTextField(15);

        JButton jButton = new JButton("读卡");

        JButton activeButton = new JButton("激活");

        JLabelEnh info = new JLabelEnh();
        Color errorColor = new Color(160,76,74);
        Color infoColor = new Color(187,187,187);
        info.setForeground(errorColor);

        SpringLayout.Constraints icIDLCons = springLayout.getConstraints(icIDLabel);
        icIDLCons.setX(Spring.constant(50));
        icIDLCons.setY(Spring.constant(50));

        SpringLayout.Constraints icIDFCons = springLayout.getConstraints(icIDField);
        icIDFCons.setConstraint(SpringLayout.WEST, Spring.sum(icIDLCons.getConstraint(SpringLayout.EAST), Spring.constant(5)));
        icIDFCons.setConstraint(SpringLayout.NORTH, icIDLCons.getConstraint(SpringLayout.NORTH));

        jButton.addActionListener(e -> {
            String id = ICUtils.readID();
            icIDField.setText(id);
        });

        activeButton.addActionListener(e -> {
            H2Config.execute("INSERT INTO ");
        });

        SpringLayout.Constraints jBCons = springLayout.getConstraints(jButton);
        jBCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(icIDLCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
        jBCons.setConstraint(SpringLayout.WEST, icIDLCons.getConstraint(SpringLayout.WEST));

        SpringLayout.Constraints acBCons = springLayout.getConstraints(activeButton);
        acBCons.setConstraint(SpringLayout.WEST, Spring.sum(jBCons.getConstraint(SpringLayout.EAST), Spring.constant(5)));
        acBCons.setConstraint(SpringLayout.NORTH, jBCons.getConstraint(SpringLayout.NORTH));

        SpringLayout.Constraints infoCons = springLayout.getConstraints(info);
        infoCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(jBCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
        infoCons.setConstraint(SpringLayout.WEST, icIDLCons.getConstraint(SpringLayout.WEST));

        this.add(icIDLabel);
        this.add(icIDField);
        this.add(jButton);
        this.add(activeButton);
        this.add(info);


        this.setVisible(true);


    }
}
