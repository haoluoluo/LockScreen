package comp;

import Config.Config;
import Config.UserConfig;
import comp.base.JPanelEnh;
import enums.CardType;
import enums.UserStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


/**
 * @author luoluo.hao
 * @create 2022-02-14 11:40
 **/
public class LoginPanel extends JPanelEnh {
    private static final long serialVersionUID = 1L;

    JPasswordField passwordText = new JPasswordField(15);

    public LoginPanel(MainWindows mainWindows) {

        this.setTransparent();

        this.setLayout(new GridLayout() );

        JPanelEnh jPanel = new JPanelEnh(new GridBagLayout());
        jPanel.setTransparent();

        jPanel.add(passwordText);
        this.add(jPanel);

        passwordText.addKeyListener(new KeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e)
            {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(UserConfig.checkPassword(String.valueOf(passwordText.getPassword()))){
                        Config.setUserStats(UserStatus.ADMIN_LOGIN);
                        mainWindows.setAlwaysOnTop(false);
                        mainWindows.changeCard(CardType.BACKGROUND);
                    }
                    passwordText.setText("");
                }
            }
        });

        setVisible(true);
    }

}
