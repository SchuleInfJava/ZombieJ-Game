package MenuUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class ImageLoader {
	
	BufferedImage qr;
	BufferedImage map;
	BufferedImage wasd;
	BufferedImage arrows;
	BufferedImage shop;
	public BufferedImage pistol;
	public BufferedImage mp;
	
	Icon quit;	

    public ImageLoader(){

        try {
            pistol = ImageIO.read(new File("rsc/pistol.png")); 
            mp  = ImageIO.read(new File("rsc/mp.png"));
            
            map = ImageIO.read(new File("rsc/dummys/map.png")); 
            wasd  = ImageIO.read(new File("rsc/dummys/wasd.png"));
            arrows  = ImageIO.read(new File("rsc/dummys/arrows.png"));
            shop  = ImageIO.read(new File("rsc/dummys/shop.png"));
            qr  = ImageIO.read(new File("rsc/dummys/qr.png"));
            quit = new ImageIcon("rsc/dummys/quit.png");   
            
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }
}
