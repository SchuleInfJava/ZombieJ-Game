package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class ImageLoader {

	
	BufferedImage image;
	
	Icon icon;

    public ImageLoader(){

        try {
            image = ImageIO.read(new File("pics/image.png")); 
            icon = new ImageIcon("pics/image.png");
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
