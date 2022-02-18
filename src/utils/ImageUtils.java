package utils;

import javax.swing.*;
import java.awt.*;

/**
 * @author luoluo.hao
 * @create 2022-02-17 0:50
 **/
public class ImageUtils {
    public static ImageIcon change(ImageIcon image, double i){//  i 为放缩的倍数

        int width=(int) (image.getIconWidth()*i);
        int height=(int) (image.getIconHeight()*i);
        Image img=image.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
        ImageIcon image2=new ImageIcon(img);

        return image2;
        }
}
