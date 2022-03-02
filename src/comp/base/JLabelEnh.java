package comp.base;


import javax.swing.*;
/**
 * @author luoluo.hao
 * @create 2022-02-18 22:09
 **/
public class JLabelEnh extends JLabel {

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
