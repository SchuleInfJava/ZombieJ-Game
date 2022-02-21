package MenuUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;



public class ImageLoader {

	
	BufferedImage image;
	BufferedImage dummy;
	Icon dreieck;
	


    public ImageLoader(){

        try {
        	
            //image = ImageIO.read(new File("rsc/background.png"));
            dummy = ImageIO.read(new File("rsc/dummys/4.png")); 
            //dreieck = new ImageIcon("rsc/dreieck.png");
            
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
