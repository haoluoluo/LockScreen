package comp;

import javax.swing.*;
import java.awt.*;

/**
 * @author luoluo.hao
 * @create 2022-02-16 16:01
 **/
public class ImagePanel extends JComponent {
        private final Image image;
        public ImagePanel(Image image) {
                this.image = image;
        }
        @Override
        protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, this);
        }
}
