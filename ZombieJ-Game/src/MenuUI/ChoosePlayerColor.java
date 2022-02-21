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
 
public class ChoosePlayerColor extends JPanel implements ChangeListener{
     
    //private final GamePanel playerGame =;
    private final Player player = GameWindow.gamepanel.getPlayer();
     
   
    private JPanel colorChooserPanel;
    private JColorChooser cc= new JColorChooser();
    private JLabel playerlabel,cannonlabel, pcostlabel,ccostlabel, allcostlabel;
     
    private static JButton setPlayerColorButton ;
    private static JButton setCannonColorButton; 
    private static JButton cancelButton = new JButton("Cancel");
    private static JButton applyButton = new JButton("Apply");
     
    private Color oldPlayerColor;
    private Color oldCannonColor;
    private Color tempPlayerColor;
    private Color tempCannonColor;
	public  List<Color> playercolors;
	public List<Color> cannoncolors;
    
    private boolean playerColor;
    private int pmoney=0;
    private int cmoney=0;
    private int amoney=0;
     
    public ChoosePlayerColor() {
    	
    	playercolors = new LinkedList<>();
    	cannoncolors = new LinkedList<>();
    	playercolors.add(player.getPlayerColor());
    	cannoncolors.add(player.getCannonColor());
        this.setFocusable(true);
        this.setPreferredSize(new Dimension(600,600));
        this.setVisible(true);
        this.setBackground(new Color(87,120,150));
        this.add(createPreviewPanel());
        this.add(createButtonsPanel());        
        registerButtonListeners();
         
        
    }    
     
    
    //Ist das PlayerVorschauPanel was hier erzeugt wird
    private JPanel createPreviewPanel() {
        JLabel titleLabel = new JLabel("Player Preview");
        titleLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
		

      
        JPanel previewPanel = new JPanel();        
        previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
        previewPanel.setBackground(new Color(87,120,150));
        previewPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        previewPanel.add(titleLabel);
        previewPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        previewPanel.add(new PlayerVorschau(player));
         
        return previewPanel;
    }
     
    //ein Panel Unter dem PreviewPanel was die Buttons umfasst
    private JPanel createButtonsPanel() { 
    	
    	playerlabel = new JLabel("Choose Player Color");
        playerlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        playerlabel.setForeground(Color.BLACK);
        playerlabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        playerlabel.setVisible(false);
        
    	cannonlabel = new JLabel("Choose Cannon Color");
        cannonlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
        cannonlabel.setForeground(Color.BLACK);
        cannonlabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        cannonlabel.setVisible(false);
        
    	
		pcostlabel = new JLabel("Player-Cost:" +pmoney);
		pcostlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		pcostlabel.setForeground(Color.BLACK);
        
    	ccostlabel = new JLabel("Cannon-Cost:"+cmoney);
    	ccostlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	ccostlabel.setForeground(Color.BLACK);
    	
    	allcostlabel = new JLabel("All-Cost:"+amoney);
    	allcostlabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	allcostlabel.setForeground(Color.BLACK);

        
    	setPlayerColorButton = new JButton("Set Player Color");
        setPlayerColorButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	setPlayerColorButton.setForeground(Color.BLACK);
    	setPlayerColorButton.setBackground(new Color(187,187,187));
    	setPlayerColorButton.setFocusPainted(false);
    	setPlayerColorButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	
    	setCannonColorButton =new JButton("Set Cannon Color");
    	setCannonColorButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	setCannonColorButton.setForeground(Color.BLACK);
    	setCannonColorButton.setBackground(new Color(187,187,187));
    	setCannonColorButton.setFocusPainted(false);
    	setCannonColorButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	
    	cancelButton =new JButton("Cancel");
    	cancelButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	cancelButton.setForeground(Color.BLACK);
    	cancelButton.setBackground(new Color(187,187,187));
    	cancelButton.setFocusPainted(false);
    	cancelButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	
    	applyButton =new JButton("Apply");
    	applyButton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
    	applyButton.setForeground(Color.BLACK);
    	applyButton.setBackground(new Color(187,187,187));
    	applyButton.setFocusPainted(false);
    	applyButton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
    	
    	cc.getSelectionModel().addChangeListener(this);
        //remove preview Field and other panels
        cc.setPreviewPanel(new JPanel());
        
        cc.setBackground(Color.YELLOW);
        cc.removeChooserPanel(cc.getChooserPanels()[4]);
        cc.removeChooserPanel(cc.getChooserPanels()[3]);
        cc.removeChooserPanel(cc.getChooserPanels()[2]);
        cc.removeChooserPanel(cc.getChooserPanels()[1]);
        
       
        JPanel colorButtonsPanel = new JPanel();
        colorButtonsPanel.setBackground(new Color(87,120,150));
        colorButtonsPanel.add(setPlayerColorButton);
        colorButtonsPanel.add(setCannonColorButton);
        
        JPanel costPanel=new JPanel();
        costPanel.setBackground(new Color(87,120,150));
        costPanel.add(pcostlabel);
        costPanel.add(ccostlabel);
        costPanel.add(allcostlabel);
        
        colorChooserPanel = new JPanel();
        colorChooserPanel.setLayout(new BoxLayout(colorChooserPanel, BoxLayout.Y_AXIS));
        colorChooserPanel.setBackground(new Color(87,120,150));
        colorChooserPanel.add(playerlabel);
        colorChooserPanel.add(cannonlabel);
        colorChooserPanel.add(cc);
        colorChooserPanel.add(costPanel);
        colorChooserPanel.setVisible(false);
        
        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setBackground(new Color(87,120,150));
        mainButtonsPanel.add(cancelButton);
        mainButtonsPanel.add(applyButton);
         
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        buttonsPanel.add(colorButtonsPanel);
        buttonsPanel.add(colorChooserPanel);
        buttonsPanel.add(new JSeparator());
        buttonsPanel.add(mainButtonsPanel);
         
        return buttonsPanel;
    }
     
    
    //Actions für die einzelnen Buttons
    private void registerButtonListeners() {
              
    	oldPlayerColor = player.getPlayerColor();  
        tempPlayerColor = oldPlayerColor;
         
        oldCannonColor = player.getCannonColor();
        tempCannonColor = oldCannonColor;
         
        setPlayerColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     
            	playerColor=true;
            	colorChooserPanel.setVisible(true);
            	playerlabel.setVisible(true);
            	cannonlabel.setVisible(false);
            	
            	
            }
        });
         
        setCannonColorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {  
            	playerColor=false;
            	colorChooserPanel.setVisible(true);
            	playerlabel.setVisible(false);
            	cannonlabel.setVisible(true);
            	
            	
            }
        });
         
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { 
                player.setPlayerColor(oldPlayerColor);
                player.setCannonColor(oldCannonColor);
                 
                tempPlayerColor = oldPlayerColor;
                tempCannonColor = oldCannonColor;
                colorChooserPanel.setVisible(false);
                 
                GameWindow.Gameframe.add(GameWindow.gamepanel);
                GameWindow.Gameframe.remove(GameWindow.choosePlayerColor);
                
                GameWindow.Gameframe.revalidate();
                GameWindow.Gameframe.repaint();
	            GameWindow.Gameframe.setSize(1000,840);
	            GameWindow.Gameframe.setLocationRelativeTo(null);
            }
        });
         
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if(GameWindow.gamepanel.getMoney()>=amoney) {
            		
            	
                oldPlayerColor = player.getPlayerColor();
                oldCannonColor = player.getCannonColor();
                playercolors.add(oldPlayerColor);
                cannoncolors.add(oldCannonColor);
                colorChooserPanel.setVisible(false);
                GameWindow.gamepanel.setMoney(GameWindow.gamepanel.getMoney()-amoney);

                 
                GameWindow.choosePlayerColor.repaint();   
                GameWindow.Gameframe.add(GameWindow.gamepanel);
                GameWindow.Gameframe.remove(GameWindow.choosePlayerColor);
                
                GameWindow.Gameframe.revalidate();
                GameWindow.Gameframe.repaint();
	            GameWindow.Gameframe.setSize(1000,840);
	            GameWindow.Gameframe.setLocationRelativeTo(null);
            	}
                
            }
        });
     
    }


	@Override
	public void stateChanged(ChangeEvent e) {
		if(playerColor==true) {
			Color newPlayerColor= cc.getColor();        
            for(Color i:playercolors) {
            	if(i==newPlayerColor) {
            		pmoney=0;
            		System.out.println(pmoney);
            	}else {
            		pmoney=30;
            		System.out.println(pmoney);
            	}
            }
	    	if (newPlayerColor != null) {     
	            tempPlayerColor = newPlayerColor;
	            player.setPlayerColor(newPlayerColor);
	            GameWindow.choosePlayerColor.repaint();
	        }
		}else {
            Color newCannonColor = cc.getColor();
            for(Color i:cannoncolors) {
            	if(i==newCannonColor) {
            		cmoney=0;
            		System.out.println(cmoney);
            	}else {
            		cmoney=30;
            		System.out.println(cmoney);
            	}
            }
            
            if (newCannonColor != null) { 
                tempCannonColor = newCannonColor;
                player.setCannonColor(newCannonColor);
                GameWindow.choosePlayerColor.repaint();
            }
		}
		
		amoney=pmoney+cmoney;

    }
	
	@Override
    protected void paintComponent(Graphics g) {  
		super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //g.setBackground();
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
		g.setColor(Color.BLACK);
        g.drawString("Player-Cost:"+pmoney,10,500);
        g.drawString("Cannon-Cost:"+cmoney,175,500);
        g.drawString("All-Cost:"+amoney,340,500);
    }
	
 
}
