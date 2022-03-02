package comp;


import comp.base.JLabelEnh;
import comp.base.JPanelEnh;
import database.Database;
import utils.ICUtils;

import javax.swing.*;
import java.awt.*;

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

        JLabelEnh icIDLabelName = new JLabelEnh("卡名: ");

        JTextField icIDFieldName = new JTextField(15);

        JButton jButton = new JButton("读卡");


        JButton activeButton = new JButton("激活");

        JPanelEnh pPanel = new JPanelEnh();
        JLabel permissionLabel =new JLabel("证件类型：");
        JComboBox<String> cmb=new JComboBox<>();
        cmb.addItem("N");
        cmb.addItem("S");
        pPanel.add(permissionLabel);
        pPanel.add(cmb);


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
            String id = ICUtils.readIDToNumber(true);
            icIDField.setText(id);
        });

        activeButton.addActionListener(e -> {

            Database.deleteCard(icIDField.getText());
            Database.insertCard(icIDField.getText(), icIDFieldName.getText(), String.valueOf(cmb.getSelectedItem()) );
            if(Database.cardExit(icIDField.getText())
                    && ICUtils.updateKey()){

                info.setForeground(infoColor);
                info.setText("激活成功");
            }else {
                info.setForeground(errorColor);
                info.setText("激活失败");
            }
        });

        SpringLayout.Constraints labelNameCons = springLayout.getConstraints(icIDLabelName);
        labelNameCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(icIDLCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
        labelNameCons.setConstraint(SpringLayout.WEST, icIDLCons.getConstraint(SpringLayout.WEST));

        SpringLayout.Constraints fieldNameCons = springLayout.getConstraints(icIDFieldName);
        fieldNameCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(icIDLCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
        fieldNameCons.setConstraint(SpringLayout.EAST, icIDFCons.getConstraint(SpringLayout.EAST));


        SpringLayout.Constraints pCons = springLayout.getConstraints(pPanel);
        pCons.setConstraint(SpringLayout.WEST, labelNameCons.getConstraint(SpringLayout.EAST));
        pCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(labelNameCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));


        SpringLayout.Constraints jBCons = springLayout.getConstraints(jButton);
        jBCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(pCons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
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
        this.add(icIDLabelName);
        this.add(icIDFieldName);
        this.add(pPanel);
        this.add(jButton);
        this.add(activeButton);
        this.add(info);


        this.setVisible(true);


    }
}
