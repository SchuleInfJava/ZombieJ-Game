package MenuUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Objects.*;
import config.*;
 
public class Shop extends JPanel implements ChangeListener{
     
    private final Player player = GameWindow.gamePanel.getPlayer();
    private final Player dummyplayer=new Player(new Coordinate(120, 180), 60, 60, Math.toRadians(0), 0);//für Vorschau
    
    private static JColorChooser cc;
    private static JButton playerColorButton,cannonColorButton,cancelButton,applyButton;
    
    

     
    private Color oldPlayerColor,oldCannonColor,tempPlayerColor,tempCannonColor;
    public List<Color> playercolors,cannoncolors;//Um gekaufte Farben zu Speichern
	
    private boolean playerColor=true;
    private boolean enoughMoney=true;//Prüft ob man genug geld hat
    private int pmoney=0;
    private int cmoney=0;
    private int amoney=0;

    
    
     
    public Shop() {
    	
    	playercolors = new LinkedList<>();
    	cannoncolors = new LinkedList<>();
    	playercolors.add(player.getPlayerColor());
    	cannoncolors.add(player.getCannonColor());
  
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(550,580));
        this.setLayout(null);
        this.setVisible(true);
        this.setBackground(new Color(87,120,150));
        
        createButton();
        buttonListeners();
      
    }  
    
    public void createButton() {
    	playerColorButton = new JButton("Set Player Color");
    	playerColorButton.setBounds(40,320,220,50);
        playerColorButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	playerColorButton.setForeground(Color.BLACK);
    	playerColorButton.setBackground(new Color(187,187,187));
    	playerColorButton.setFocusPainted(true);
    	playerColorButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
        playerColorButton.setVisible(true);
    	add(playerColorButton);
    	
    	cannonColorButton =new JButton("Set Weapon Color");
    	cannonColorButton.setBounds(280,320,220,50);
    	cannonColorButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	cannonColorButton.setForeground(Color.BLACK);
    	cannonColorButton.setBackground(new Color(187,187,187));
    	cannonColorButton.setFocusPainted(true);
    	cannonColorButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	cannonColorButton.setVisible(true);
    	this.add(cannonColorButton);
    	
    	cancelButton =new JButton("Cancel");
    	cancelButton.setBounds(90,560,160,40);
    	cancelButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	cancelButton.setForeground(Color.BLACK);
    	cancelButton.setBackground(new Color(187,187,187));
    	cancelButton.setFocusPainted(true);
    	cancelButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	cancelButton.setVisible(true);
    	this.add(cancelButton);
    	
    	applyButton =new JButton("Buy");
    	applyButton.setBounds(290,560,160,40);
    	applyButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	applyButton.setForeground(Color.BLACK);
    	applyButton.setBackground(new Color(187,187,187));
    	applyButton.setFocusPainted(true);
    	applyButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	applyButton.setVisible(true);
    	this.add(applyButton);
    	
    	cc=new JColorChooser();
    	cc.setBounds(50,420,430,130);
        cc.setPreviewPanel(new JPanel());//entfernt PreviewPanel
        cc.removeChooserPanel(cc.getChooserPanels()[4]);
        cc.removeChooserPanel(cc.getChooserPanels()[3]);
        cc.removeChooserPanel(cc.getChooserPanels()[2]);
        cc.removeChooserPanel(cc.getChooserPanels()[1]);
        cc.getSelectionModel().addChangeListener(this);
        cc.setVisible(true);
        this.add(cc);
        
    	
    }
    
    //Actions für die einzelnen Buttons
    private void buttonListeners() {
              
    	oldPlayerColor = player.getPlayerColor();  
        tempPlayerColor = oldPlayerColor;
         
        oldCannonColor = player.getCannonColor();
        tempCannonColor = oldCannonColor;
         
        playerColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	playerColor=true;
            	repaint();
            }
        });
         
        cannonColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            	playerColor=false;  	
            	repaint();
         }
        });
         
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
            	
            	GameWindow.gamePanel.setOtherJPanel(false);
            	playerColor=true;
            	enoughMoney=true;
            	pmoney=0;
            	cmoney=0;
            	amoney=0;
            	dummyplayer.setPlayerColor(oldPlayerColor);
            	dummyplayer.setCannonColor(oldCannonColor);
                player.setPlayerColor(oldPlayerColor);
                player.setCannonColor(oldCannonColor);
                 
                tempPlayerColor = oldPlayerColor;
                tempCannonColor = oldCannonColor;
                 
                GameWindow.gameFrame.add(GameWindow.gamePanel);
                GameWindow.gameFrame.remove(GameWindow.shop);
                
                GameWindow.gameFrame.revalidate();
                GameWindow.gameFrame.repaint();
	            GameWindow.gameFrame.setSize(1000,840);
	            GameWindow.gameFrame.setLocationRelativeTo(null);
            }
        });
         
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(GameWindow.gamePanel.getMoney()>=amoney) {
            		//Variablen reset
            		GameWindow.gamePanel.setOtherJPanel(false);
                	playerColor=true;
                	enoughMoney=true;
                	pmoney=0;
                	cmoney=0;
            	    oldPlayerColor = dummyplayer.getPlayerColor();
                    oldCannonColor = dummyplayer.getCannonColor();
                    
                    //Farben werden überschrieben
                	dummyplayer.setPlayerColor(oldPlayerColor);
                	dummyplayer.setCannonColor(oldCannonColor);            	
                	player.setPlayerColor(oldPlayerColor);
                	player.setCannonColor(oldCannonColor);
               
                	//Gekaufte Liste und Geld abzug
                    playercolors.add(oldPlayerColor);
                    cannoncolors.add(oldCannonColor);
                    GameWindow.gamePanel.setMoney(GameWindow.gamePanel.getMoney()-amoney);
                    amoney=0;

                    
                GameWindow.gameFrame.add(GameWindow.gamePanel);
                GameWindow.gameFrame.remove(GameWindow.shop);
                GameWindow.gameFrame.revalidate();
                GameWindow.gameFrame.repaint();
	            GameWindow.gameFrame.setSize(1000,840);
	            GameWindow.gameFrame.setLocationRelativeTo(null);
            	}else {
            		enoughMoney=false;
            		repaint();
            	}
                
            }
        });
     
    }


	@Override
	public void stateChanged(ChangeEvent e) {

		if(playerColor==true) {
			Color newPlayerColor= cc.getColor();

			for(Iterator<Color> itPColor = playercolors.iterator(); itPColor.hasNext();) {
            	Color pcolor= itPColor.next();

            	if(pcolor.equals(newPlayerColor)) {
            		pmoney=0;
            		break;	
            	}else {
            		pmoney=30;	
            	}
            }
			
	    	if (newPlayerColor != null) {     
	            tempPlayerColor = newPlayerColor;
	            dummyplayer.setPlayerColor(newPlayerColor);
	            GameWindow.shop.repaint();
	        }
		}else {
            Color newCannonColor = cc.getColor();
            
            for(Color i:cannoncolors) {
            	if(i.equals(newCannonColor)) {
            		cmoney=0;
            		break;
            	}else {
            		cmoney=30;	
            	}
            }
            
            if (newCannonColor != null) { 
                tempCannonColor = newCannonColor;
                dummyplayer.setCannonColor(newCannonColor);
                GameWindow.shop.repaint();
            }
		}
		
		amoney=pmoney+cmoney;
    }
	
	@Override
    public void paintComponent(Graphics g) {  
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);     
        
        //Header
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
        g2d.setColor(Color.BLACK);
        g.drawString("Shop",160,80);
        g2d.setStroke(new BasicStroke(5));
        g.drawLine(160,83,360,83);
        
        
        
        //Player Preview
        g.setColor(Color.WHITE);
        g.fillRect(40, 120, 240-1, 180-1);
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRect(40, 120, 240, 180); 
        
        dummyplayer.setPaintStatusBar(false);
        dummyplayer.paintMe(g);
        dummyplayer.setPaintStatusBar(true);
        

        
        //Subheader
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
        g.setColor(Color.BLACK);
        g.drawString("Preview:",45,140);
        
        //Costs
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 26));
		g.setColor(Color.BLACK);
		g.drawString("Money: "+GameWindow.gamePanel.getMoney()+"$",305,145);
        g.drawString("Player-Cost:"+pmoney,305,185);
        g.drawString("Weapon-Cost:"+cmoney,305,225);
        g.drawString("All-Cost:"+amoney,305,265);
        
        if(enoughMoney==false) {
            g.setColor(Color.RED);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
            g.drawString("Not enough Money",305,295);	
        }

        
        //Choose Color
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		g.setColor(new Color(240,240,240));
		g.fillRect(30,380,475,240);
		g.setColor(Color.BLACK);
		g.drawRect(30,380,475,240);
		if(playerColor==true) {
			g.drawString("Choose Player Color",100,415);
		}else {
			g.drawString("Choose Weapon Color",100,415);
		}
        
		g2d.setStroke(new BasicStroke(1));
      
    }
	
 
}
