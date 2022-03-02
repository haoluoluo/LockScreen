package comp;


import Config.Config;
import comp.base.JLabelEnh;
import comp.base.JPanelEnh;
import database.Database;
import utils.PropertiesUtils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author luoluo.hao
 * @create 2022-02-19 19:23
 **/
public class AppearancePanel extends JPanelEnh {
    public AppearancePanel(){

        this.setTransparent();

        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        JLabel shutdownLabel =new JLabel("刷出后是否关机：");

        ButtonGroup buttonGroup = new ButtonGroup();
        JRadioButton yesRB = new JRadioButton("是");


        JRadioButton noRB = new JRadioButton("否");

        if(Boolean.parseBoolean(Config.PROPERTIES.getProperty(Config.OUT_SHUTDOWN_PROPERTY))){
            yesRB.setSelected(true);
        }else {
            noRB.setSelected(true);
        }

        buttonGroup.add(yesRB);
        buttonGroup.add(noRB);

        AtomicReference<String> bgImageFile = new AtomicReference<>("");
        JButton changeButton = new JButton("修改背景图片");




        changeButton.addActionListener(e -> {
            JFileChooser jfc=new JFileChooser();
            jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
            jfc.setDialogTitle("选择图片");
            jfc.showDialog(new JLabel(), "选择");
            File file=jfc.getSelectedFile();

            bgImageFile.set(file.getAbsolutePath());
        });


        JButton saveButton = new JButton("保存");


        JButton resumeButton = new JButton("清空所有数据");

        JLabelEnh info = new JLabelEnh();
        Color errorColor = new Color(160,76,74);
        Color infoColor = new Color(187,187,187);
        info.setForeground(errorColor);

        saveButton.addActionListener(e -> {
            info.setText("");
            int result = JOptionPane.showConfirmDialog(null, "确定要保存吗？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(Objects.equals(result, JOptionPane.YES_OPTION)){
                Config.PROPERTIES.setProperty(Config.BACKGROUND_IMAGE_PROPERTY, bgImageFile.get());
                System.out.println(Config.PROPERTIES.get(Config.BACKGROUND_IMAGE_PROPERTY));
                Config.PROPERTIES.setProperty(Config.OUT_SHUTDOWN_PROPERTY, String.valueOf(yesRB.isSelected()));
                PropertiesUtils.store(Config.SET_PROPERTIES, Config.PROPERTIES);
                info.setForeground(infoColor);
                info.setText("保存成功");
            }
        });
        resumeButton.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "确定要清空吗？", "提示", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if(Objects.equals(result, JOptionPane.YES_OPTION)) {
                info.setText("");
                Database.deletAllCard();
                Database.deleteAllHistory();
                info.setForeground(infoColor);
                info.setText("已清空数据");
            }
        });


        SpringLayout.Constraints shutdownLabelCons = springLayout.getConstraints(shutdownLabel);
        shutdownLabelCons.setX(Spring.constant(50));
        shutdownLabelCons.setY(Spring.constant(50));

        SpringLayout.Constraints yesButtonCons = springLayout.getConstraints(yesRB);
        yesButtonCons.setConstraint(SpringLayout.WEST, Spring.sum(
                shutdownLabelCons.getConstraint(SpringLayout.EAST),
                Spring.constant(10)
        ));
        yesButtonCons.setConstraint(SpringLayout.NORTH, shutdownLabelCons.getConstraint(SpringLayout.NORTH));

        SpringLayout.Constraints noButtonCons = springLayout.getConstraints(noRB);
        noButtonCons.setConstraint(SpringLayout.WEST, Spring.sum(
                yesButtonCons.getConstraint(SpringLayout.EAST),
                Spring.constant(3)
        ));
        noButtonCons.setConstraint(SpringLayout.NORTH, shutdownLabelCons.getConstraint(SpringLayout.NORTH));

        SpringLayout.Constraints changeCons = springLayout.getConstraints(changeButton);
        changeCons.setConstraint(SpringLayout.WEST, shutdownLabelCons.getConstraint(SpringLayout.WEST));
        changeCons.setConstraint(SpringLayout.NORTH, Spring.sum(
                shutdownLabelCons.getConstraint(SpringLayout.SOUTH),
                Spring.constant(20)
        ));

        SpringLayout.Constraints saveCons = springLayout.getConstraints(saveButton);
        saveCons.setConstraint(SpringLayout.WEST, shutdownLabelCons.getConstraint(SpringLayout.WEST));
        saveCons.setConstraint(SpringLayout.NORTH, Spring.sum(
                changeCons.getConstraint(SpringLayout.SOUTH),
                Spring.constant(20)
        ));

        SpringLayout.Constraints resumeCons = springLayout.getConstraints(resumeButton);
        resumeCons.setConstraint(SpringLayout.WEST, shutdownLabelCons.getConstraint(SpringLayout.WEST));
        resumeCons.setConstraint(SpringLayout.NORTH, Spring.sum(
                saveCons.getConstraint(SpringLayout.SOUTH),
                Spring.constant(20)
        ));

        SpringLayout.Constraints infoCons = springLayout.getConstraints(info);
        infoCons.setConstraint(SpringLayout.WEST, shutdownLabelCons.getConstraint(SpringLayout.WEST));
        infoCons.setConstraint(SpringLayout.NORTH, Spring.sum(
                resumeCons.getConstraint(SpringLayout.SOUTH),
                Spring.constant(20)
        ));

        this.add(shutdownLabel);
        this.add(yesRB);
        this.add(noRB);

        this.add(changeButton);
        this.add(saveButton);
        this.add(resumeButton);
        this.add(info);


        this.setVisible(true);


    }
}
