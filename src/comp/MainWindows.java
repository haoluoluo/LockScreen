import Config.Config;
import comp.ImagePanel;
import enums.CardType;
import utils.FileUtils;
import utils.ThreadPoolUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author luoluo.hao
 * @create 2022-02-16 20:58
 **/
public class MainWindows extends JFrame {

    private final AtomicBoolean show = new AtomicBoolean(true);
    /**
     * 创建并显示GUI。出于线程安全的考虑，
     * 这个方法在事件调用线程中调用。
     */
    public MainWindows() {

        super();

        this.init();

        this.setLayout(new CardLayout());
        Container contentPane = this.getContentPane();

        contentPane.setLayout(new CardLayout());

        InformationPanel informationPanel = new InformationPanel(this);

        LoginPanel loginPanel = new LoginPanel(this);

        contentPane.add(informationPanel, CardType.INFORMATION);
        contentPane.add(loginPanel, CardType.LOGIN);

        changeCard(CardType.INFORMATION);

//        // 显示窗口LOGIN
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowActivated(WindowEvent e) {
                Config.LOGIN_STATUS.set(false);
                show.set(false);
            }
            @Override
            public void windowClosing(WindowEvent e) {
            }
            @Override
            public void windowDeactivated(WindowEvent e) {
                if(!Config.LOGIN_STATUS.get()){
                    show.set(true);
                    ThreadPoolUtils.executor(()->{
                        while (show.get()){
                            maximize();
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException interruptedException) {
                                interruptedException.printStackTrace();
                            }
                        }
                    });
                }
            }
        });
    }

    public void init(){
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//        Rectangle bounds = new Rectangle(screenSize);
//        frame.setBounds(bounds);

        this.setAlwaysOnTop(true);
//        frame.setResizable(false);
//        frame.setUndecorated(true);
        this.setSize(1000, 1000);
//        setSize(frame);
//        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        setIcon(this);
        this.setVisible(true);
    }
    public static void setIcon(JFrame frame){
        //设置logo
        Toolkit toolkit=frame.getToolkit();
        URL resource = FileUtils.loadResource(Config.LOGO);
        Image topic=toolkit.getImage(resource);
        frame.setIconImage(topic);


//        设置背景图片
        URL backUrl = FileUtils.loadResource(Config.BACKGROUND_IMAGE);
        ImageIcon imageicon = new ImageIcon(backUrl);
        Image backImage = imageicon.getImage();
        if(frame.getHeight()>0 && frame.getWidth()>0){
            backImage = (imageicon).getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        }
        frame.setContentPane(new ImagePanel(backImage));
    }

    public void changeCard(String card){
        Container contentPane = this.getContentPane();
        ((CardLayout)contentPane.getLayout()).show(contentPane, card);
    }
    public void minimize(){
        this.setExtendedState(JFrame.ICONIFIED);
    }

    public void maximize(){
//        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
//        this.setExtendedState(JFrame.NORMAL);
    }
}
