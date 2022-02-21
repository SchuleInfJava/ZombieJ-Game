package MenuUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

import Objects.Player;

public class PlayerVorschau extends JPanel{
	private final Player player;
    
    private final int panelWidth;
    private final int panelHeight;    
    private final int playerWidth;
    private final int playerHeight;
     
    public PlayerVorschau (Player player) {
                
        this.player = player;
         
        playerWidth  = (int)player.getWidth();
        playerHeight = (int)player.getHeight();
         
        panelWidth  = playerWidth * 4;
        panelHeight = playerHeight * 3;
         
        setPreferredSize(new Dimension(panelWidth, panelHeight)); 
    }
     
    @Override
    protected void paintComponent(Graphics g) {        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                 
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, panelWidth-1, panelHeight-1);
         
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(6));
        g2d.drawRect(0, 0, panelWidth, panelHeight);        
                 
        int playerPosX = (int)player.getObjectPosition().getX();
        int playerPosY = (int)player.getObjectPosition().getY();
                 
        g.translate(panelWidth/2 - playerWidth/2 - playerPosX, panelHeight/2 - playerHeight/2 - playerPosY); 
        player.setPaintStatusBar(false);
        player.paintMe(g);
        player.setPaintStatusBar(true);
    }
}

