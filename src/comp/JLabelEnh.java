package comp;

import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author luoluo.hao
 * @create 2022-02-18 22:09
 **/
public class JLabelEnh extends JLabel {

    public void setImage(String path){
        URL backUrl = FileUtils.loadResource(path);
        ImageIcon imageicon = new ImageIcon(backUrl);
        Image backImage = imageicon.getImage();
        if(this.getHeight()>0 && this.getWidth()>0){
            backImage = (imageicon).getImage().getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
            this.setIcon(new ImageIcon(backImage));
        }
    }

    public void setTransparent(){
        this.setBackground(null);
        this.setOpaque(false);
    }
    public JLabelEnh(String text){
        super(text);
    }
    public  JLabelEnh(){
        super();
    }
}
