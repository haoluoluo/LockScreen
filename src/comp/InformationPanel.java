package comp;

import Config.Config;
import enums.CardType;
import utils.FileUtils;
import utils.ImageUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.net.URL;

/**
 * @author luoluo.hao
 * @create 2022-02-14 14:45
 **/
public class InformationPanel extends JPanelEnh {

    public InformationPanel(MainWindows frame) {

        //设置透明
        this.setTransparent();

        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        GridBagConstraints constraints = new GridBagConstraints();

        URL backUrl = FileUtils.loadResource(Config.INFORMATION_IMAGE);

        ImageIcon imageIcon = ImageUtils.change(new ImageIcon(backUrl), 0.6);


        JLabel label = new JLabel(imageIcon);
        label.setForeground(Color.red);
        label.setVisible(true);


        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                super.mouseClicked(e);
                if(e.getClickCount()== Config.CLICK_COUNT){
                    frame.changeCard(CardType.LOGIN);
                }
            }
        });

        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.weightx = 1;
        constraints.weighty = 1;

        gridBagLayout.setConstraints(label, constraints);


        this.add(label);

        this.setVisible(true);
    }
}
