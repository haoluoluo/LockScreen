package comp.base;

import comp.ImagePanel;
import utils.FileUtils;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

/**
 * @author luoluo.hao
 * @create 2022-02-16 21:46
 **/
public class JPanelEnh extends JPanel {

    public void setImage(String path){
        URL backUrl = FileUtils.loadResource(path);
        ImageIcon imageicon = new ImageIcon(backUrl);
        Image backImage = imageicon.getImage();
        if(this.getHeight()>0 && this.getWidth()>0){
            backImage = (imageicon).getImage().getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        }
        this.add(new ImagePanel(backImage));
        this.setVisible(true);
    }

    public void setTransparent(){
        this.setBackground(null);
        this.setOpaque(false);
    }


    public JPanelEnh(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    /**
     * Create a new buffered JPanel with the specified layout manager
     *
     * @param layout  the LayoutManager to use
     */
    public JPanelEnh(LayoutManager layout) {
       super(layout);
    }

    /**
     * Creates a new <code>JPanel</code> with <code>FlowLayout</code>
     * and the specified buffering strategy.
     * If <code>isDoubleBuffered</code> is true, the <code>JPanel</code>
     * will use a double buffer.
     *
     * @param isDoubleBuffered  a boolean, true for double-buffering, which
     *        uses additional memory space to achieve fast, flicker-free
     *        updates
     */
    public JPanelEnh(boolean isDoubleBuffered) {
        super(new FlowLayout(), isDoubleBuffered);
    }

    /**
     * Creates a new <code>JPanel</code> with a double buffer
     * and a flow layout.
     */
    public JPanelEnh() {
        super(true);
    }
}
