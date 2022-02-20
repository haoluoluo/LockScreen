package comp;

import comp.base.JPanelEnh;
import comp.base.JTreeEnh;
import comp.base.TreeNodeEnh;
import enums.BackgroundCardType;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import java.awt.*;

public class LeftBackgroundPanel extends JPanelEnh {
    public LeftBackgroundPanel(JPanelEnh rightPanel){
        super();
        this.setMinimumSize(new Dimension(30,30));
        SpringLayout leftSpringLayout = new SpringLayout();
        this.setLayout(leftSpringLayout);
        SpringLayout.Constraints thisCons = leftSpringLayout.getConstraints(this);
        thisCons.setWidth(Spring.constant(100));
        TreeNodeEnh root = new TreeNodeEnh();
        JTreeEnh tree = new JTreeEnh(root, rightPanel);

        //IC
        TreeNodeEnh iCNode = new TreeNodeEnh(BackgroundCardType.IC_CARD);
        TreeNodeEnh icActiveNode = new TreeNodeEnh(BackgroundCardType.IC_CARD_ACTIVE);
        iCNode.add(icActiveNode);

        root.add(iCNode);

        //set
        TreeNodeEnh setNode = new TreeNodeEnh(BackgroundCardType.SETTING);
        TreeNodeEnh changNode = new TreeNodeEnh(BackgroundCardType.CHANGE_PASSWORD);
        setNode.add(changNode);
        root.add(setNode);


        tree.expandTree();
        tree.setRootVisible(false);


//        tree.addTreeSelectionListener(rightPanel);

        this.add(tree);
    }
}
