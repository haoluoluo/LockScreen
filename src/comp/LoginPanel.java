package comp;

import Config.Config;
import Config.UserConfig;
import enums.CardType;
import enums.UserStatus;
import utils.ASC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Objects;


/**
 * @author luoluo.hao
 * @create 2022-02-14 11:40
 **/
public class LoginPanel extends JPanelEnh {
    private static final long serialVersionUID = 1L;

    JPasswordField passwordText = new JPasswordField(15);

    public LoginPanel(MainWindows frame) {

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
                    String passwd = new String(Objects.requireNonNull(ASC.encrypt(new String(passwordText.getPassword()), Config.ASC_PASSWORD)));
                    String realPd = null;
                    try {
                        realPd = UserConfig.getSuperUser().get("root");
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    if(Objects.equals(passwd, realPd)){
                        Config.setUserStats(UserStatus.ADMIN_LOGIN);
                        passwordText.setText("");
                        frame.minimize();
                        frame.changeCard(CardType.INFORMATION);
                    }
                    passwordText.setText("");
                }
            }
        });

        setVisible(true);

//        this.setPreferredSize(new Dimension(400, 150));
//        Font font = new Font("宋体", Font.PLAIN, 30);
//
//        // 创建 JLabel
//        JLabel userLabel = new JLabel("账号:");
//        userLabel.setFont(font);
//        /* 这个方法定义了组件的位置。
//         * setBounds(x, y, width, height)
//         * x 和 y 指定左上角的新位置，由 width 和 height 指定新的大小。
//         */
//        userLabel.setBounds(10,20,80,25);
////        this.add(userLabel);
//
//
//        userText.setBounds(100,20,165,25);
//        userText.setEditable(true);
//        userText.setFont(font);
////        this.add(userText);
//
//        // 输入密码的文本域
//        JLabel passwordLabel = new JLabel("密码:");
//        passwordLabel.setBounds(10,50,80,25);
//        passwordLabel.setFont(font);
////        this.add(passwordLabel);
//
//
////        passwordText.setBounds(100,50,165,25);
//        passwordText.setSize(200,20);
////        passwordText.setEditable(true);
//        passwordText.setText("123456");
//        this.add(passwordText);
//
//        // 创建登录按钮
//        JButton loginButton = new JButton("登录");
//        loginButton.setFont(font);
//        loginButton.setBounds(10, 80, 80, 25);
//
////        this.add(loginButton);
//
//        JLabel info = new JLabel();


//        loginButton.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                String user = userText.getText();
//                String passwd = new String(ASC.encrypt(new String(passwordText.getPassword()), Config.ASC_PASSWORD));
//                String realPdwd = null;
//                try {
//                    realPdwd = UserConfig.getSuperUser().get(user);
//                } catch (IOException ioException) {
//                    ioException.printStackTrace();
//                }
//                if(Objects.equals(passwd, realPdwd)){
////                    frame.setExtendedState(JFrame.ICONIFIED);
////                    info.setVisible(false);
////                    this.setVisible(false);
////                    comp.MyFrame.isLogin = true;
//                }else{
//                    info.setVisible(true);
//                }
//            }
//        });
//
//        info.setFont(font);
//        info.setForeground(Color.red);
//        info.setText("账号或者密码不正确");
//        info.setVisible(false);
////        this.add(info);
//
//
    }

}
