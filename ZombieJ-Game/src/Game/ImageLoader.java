package Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageLoader {

	
	BufferedImage image;

    public ImageLoader(){

        try {
            image = ImageIO.read(new File("picture/image.png")); 
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
