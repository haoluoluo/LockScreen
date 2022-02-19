package comp;

import com.sun.corba.se.impl.resolver.SplitLocalResolverImpl;

import javax.swing.*;
import java.awt.*;

/**
 * @author luoluo.hao
 * @create 2022-02-18 22:02
 **/
public class LeftIconJPanel extends JPanelEnh {
    public LeftIconJPanel(String image, String text){
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        Font font = new Font("宋体", Font.PLAIN, 30);
        this.setFont(font);

        JLabelEnh imageLabel = new JLabelEnh();
        imageLabel.setSize(30,30);
        imageLabel.setImage(image);
        imageLabel.setTransparent();

        JLabelEnh jLabel = new JLabelEnh(text);
        jLabel.setSize(30,30);
        jLabel.setTransparent();
        jLabel.setFont(font);

        jLabel.setForeground(Color.white);

        this.add(imageLabel);
        this.add(jLabel);

        SpringLayout.Constraints imageCons = springLayout.getConstraints(imageLabel);
        imageCons.setConstraint(SpringLayout.WEST, Spring.constant(5));


        SpringLayout.Constraints labelCons = springLayout.getConstraints(jLabel);
        labelCons.setConstraint(SpringLayout.WEST, Spring.sum(imageCons.getConstraint(SpringLayout.EAST), Spring.constant(10)));

        SpringLayout.Constraints thisCons = springLayout.getConstraints(this);
        thisCons.setX(Spring.constant(1));
        thisCons.setY(Spring.constant(1));
        thisCons.setConstraint(SpringLayout.EAST, labelCons.getConstraint(SpringLayout.EAST));
        thisCons.setConstraint(SpringLayout.NORTH, Spring.sum(labelCons.getConstraint(SpringLayout.NORTH), Spring.constant(3)));
        thisCons.setConstraint(SpringLayout.SOUTH, Spring.sum(labelCons.getConstraint(SpringLayout.SOUTH), Spring.constant(3)));
//        this.setBorder(BorderFactory.createLineBorder(Color.red));
        this.setTransparent();

        imageLabel.setVisible(true);
        jLabel.setVisible(true);
        this.setVisible(true);
    }
}
