package comp;


import javax.swing.*;
import java.util.jar.JarEntry;

/**
 * @author luoluo.hao
 * @create 2022-02-19 19:23
 **/
public class PasswdPanel extends JPanelEnh{
    public PasswdPanel(){

        this.transferFocus();
        SpringLayout springLayout = new SpringLayout();
        this.setLayout(springLayout);

        JLabelEnh oldPdwdLabel = new JLabelEnh("原密码呀: ");

        JPasswordField oldPdwdField = new JPasswordField(15);

        JLabelEnh pdwdLabel = new JLabelEnh("新密码呀: ");

        JPasswordField pdwdField = new JPasswordField(15);


        JLabelEnh pdwdLabelAgain = new JLabelEnh("确认密码: ");

        JPasswordField pdwdFieldAgain = new JPasswordField(15);

        SpringLayout.Constraints oPCons = springLayout.getConstraints(oldPdwdLabel);
        oPCons.setX(Spring.constant(50));
        oPCons.setY(Spring.constant(50));

        SpringLayout.Constraints oFCons = springLayout.getConstraints(oldPdwdField);
        oFCons.setConstraint(SpringLayout.WEST, Spring.sum(oPCons.getConstraint(SpringLayout.EAST), Spring.constant(5)));
        oFCons.setConstraint(SpringLayout.NORTH, oPCons.getConstraint(SpringLayout.NORTH));

        SpringLayout.Constraints pLCons = springLayout.getConstraints(pdwdLabel);
        pLCons.setConstraint(SpringLayout.WEST, oPCons.getConstraint(SpringLayout.WEST));
        pLCons.setConstraint(SpringLayout.EAST, oPCons.getConstraint(SpringLayout.EAST));
        pLCons.setConstraint(SpringLayout.NORTH, Spring.sum(oPCons.getConstraint(SpringLayout.SOUTH), Spring.constant(15)));

        SpringLayout.Constraints pFCons = springLayout.getConstraints(pdwdField);
        pFCons.setConstraint(SpringLayout.WEST, Spring.sum(pLCons.getConstraint(SpringLayout.EAST),
                Spring.constant(5)));
        pFCons.setConstraint(SpringLayout.NORTH, pLCons.getConstraint(SpringLayout.NORTH));

        SpringLayout.Constraints pLACons = springLayout.getConstraints(pdwdLabelAgain);
        pLACons.setConstraint(SpringLayout.WEST, oPCons.getConstraint(SpringLayout.WEST));
        pLACons.setConstraint(SpringLayout.EAST, oPCons.getConstraint(SpringLayout.EAST));
        pLACons.setConstraint(SpringLayout.NORTH, Spring.sum(pLCons.getConstraint(SpringLayout.SOUTH),
                Spring.constant(15)));

        SpringLayout.Constraints pFACons = springLayout.getConstraints(pdwdFieldAgain);
        pFACons.setConstraint(SpringLayout.WEST, Spring.sum(pLACons.getConstraint(SpringLayout.EAST), Spring.constant(5)));
        pFACons.setConstraint(SpringLayout.NORTH, pLACons.getConstraint(SpringLayout.NORTH));

        JButton jButton = new JButton("确认");

        SpringLayout.Constraints jBCons = springLayout.getConstraints(jButton);
        jBCons.setConstraint(SpringLayout.NORTH,
                Spring.sum(pLACons.getConstraint(SpringLayout.SOUTH), Spring.constant(20)));
        jBCons.setConstraint(SpringLayout.WEST, oPCons.getConstraint(SpringLayout.WEST));

        this.add(oldPdwdLabel);
        this.add(oldPdwdField);
        this.add(pdwdLabel);
        this.add(pdwdField);
        this.add(pdwdLabelAgain);
        this.add(pdwdFieldAgain);
        this.add(jButton);


        this.setVisible(true);


    }
}
