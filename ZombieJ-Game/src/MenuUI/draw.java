package MenuUI;
import javax.swing.JLabel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class draw extends JLabel{

	
ImageLoader il = new ImageLoader();
    
    protected void paintComponent(Graphics g){

        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); //weichzeihnen

        

       //g.drawImage(il.image, 50, 50, null);//kordinaten
       g.drawImage(il.dummy, 0, 0,300,200, null);//kordinaten und bild größe
       g.drawRect(0,0,350,250);
       //g.drawImage(il.dummy,200,200,400,400,null);
        repaint();
    }
}
