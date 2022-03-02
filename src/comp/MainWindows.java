package comp;

import Config.Config;
import com.formdev.flatlaf.intellijthemes.FlatArcDarkIJTheme;
import enums.CardType;
import org.apache.commons.lang3.StringUtils;
import task.SchedulerMain;
import utils.FileUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author luoluo.hao
 * @create 2022-02-16 20:58
 **/
public class MainWindows extends JFrame {

    /**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public MainWindows() {
        super();
        try {
            UIManager.setLookAndFeel( new FlatArcDarkIJTheme() );
        } catch( Exception ex ) {
            System.err.println( "Failed to initialize LaF" );
        }

        this.init();

        this.setLayout(new CardLayout());
        Container contentPane = this.getContentPane();

        contentPane.setLayout(new CardLayout());

        InformationPanel informationPanel = new InformationPanel(this);

        LoginPanel loginPanel = new LoginPanel(this);

        contentPane.add(informationPanel, CardType.INFORMATION);
        contentPane.add(loginPanel, CardType.LOGIN);

        BackgroundPanel backgroundPanel = new BackgroundPanel(this);
        contentPane.add(backgroundPanel, CardType.BACKGROUND);



        changeCard(CardType.INFORMATION);

        SchedulerMain.run(this);

        // 显示窗口LOGIN
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
            }
            @Override
            public void windowClosing(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
            }
        });
    }

    public void init(){
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.setAlwaysOnTop(true);
            this.setResizable(false);
            this.setUndecorated(true);

            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle bounds = new Rectangle(screenSize);
            this.setBounds(bounds);

            this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setIcon();
        this.setBackground();
        this.setVisible(true);
    }
    public void setIcon(){
        //设置logo
        Toolkit toolkit=this.getToolkit();
        URL resource = FileUtils.loadResource(Config.LOGO_IMAGE);
        Image topic=toolkit.getImage(resource);
        this.setIconImage(topic);
    }
    public void setBackground(){
        //设置背景图片
        Image backImage = null;
        File backgroundImage = new File(String.valueOf(Config.PROPERTIES.get(Config.BACKGROUND_IMAGE_PROPERTY)));
        if( StringUtils.isEmpty(String.valueOf(Config.PROPERTIES.get(Config.BACKGROUND_IMAGE_PROPERTY))) || !backgroundImage.exists()){
            URL backUrl = FileUtils.loadResource(Config.BACKGROUND_IMAGE);
            ImageIcon imageicon = new ImageIcon(backUrl);
            backImage = imageicon.getImage();
        }else {
            try {
                backImage = ImageIO.read(backgroundImage);
            } catch (IOException ignored) {
            }
        }
        if(this.getHeight()>0 && this.getWidth()>0){
            assert backImage != null;
            backImage = backImage.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        }
        this.setContentPane(new ImagePanel(backImage));
    }

    public void changeCard(String card){
        Container contentPane = this.getContentPane();
        ((CardLayout)contentPane.getLayout()).show(contentPane, card);
    }
    public void minimize(){
        this.setExtendedState(JFrame.ICONIFIED);
    }

    public void maximize(){
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}
