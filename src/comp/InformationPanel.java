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
public class InformationPanel extends JPanel {

    public InformationPanel(MainWindows frame) {
        this.setBackground(null);
        this.setOpaque(false);


        GridBagLayout gridBagLayout = new GridBagLayout();
        this.setLayout(gridBagLayout);

        GridBagConstraints constraints = new GridBagConstraints();

        String labelText ="<html><span>请刷卡解锁</span><br /><br /> <span>PLEASE SWIPE TO UNLOCK</span></html>";
        String a = "< br /> test";
//        JLabel label = new JLabel(labelText,JLabel.CENTER);
        URL backUrl = FileUtils.loadResource("info.png");

        ImageIcon imageIcon = ImageUtils.change(new ImageIcon(backUrl), 0.6);


        JLabel label = new JLabel(imageIcon);
//        label.setFont(new Font("宋体", Font.PLAIN, 60));
        label.setForeground(Color.red);
        label.setVisible(true);
//        label.setBorder(BorderFactory.createLineBorder(Color.red));

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
