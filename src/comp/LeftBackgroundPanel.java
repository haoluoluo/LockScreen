package comp;

import comp.base.JPanelEnh;
import comp.base.JTreeEnh;
import comp.base.TreeNodeEnh;
import enums.BackgroundCardType;

import javax.swing.*;
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
        TreeNodeEnh icCard = new TreeNodeEnh(BackgroundCardType.IC_CARD_INFO);
        TreeNodeEnh history = new TreeNodeEnh(BackgroundCardType.IC_HISTORY);
        TreeNodeEnh icActiveNode = new TreeNodeEnh(BackgroundCardType.IC_CARD_ACTIVE);
        iCNode.add(icCard);
        iCNode.add(history);
        iCNode.add(icActiveNode);

        root.add(iCNode);

        //set
        TreeNodeEnh setNode = new TreeNodeEnh(BackgroundCardType.SETTING);
        TreeNodeEnh appearanceNode = new TreeNodeEnh(BackgroundCardType.APPEARANCE);
        TreeNodeEnh changNode = new TreeNodeEnh(BackgroundCardType.CHANGE_PASSWORD);
        setNode.add(appearanceNode);
        setNode.add(changNode);
        root.add(setNode);


        tree.expandTree();
        tree.setRootVisible(false);

        this.add(tree);
    }
}
