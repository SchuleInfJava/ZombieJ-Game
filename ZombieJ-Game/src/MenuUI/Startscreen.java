package MenuUI;
 

import java.awt.BasicStroke;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Startscreen {

	static public JButton startbutton, shopbutton, settingsbutton, helpbutton, quitbutton, button;
	public JLabel Title, bg;
	
    static public JFrame frame;
    
    public Startscreen() {
		 
		 frame = new JFrame("ZombieJGame");
		 frame.setSize(800,600);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet Prozess falls geschlossen wird
		 frame.setResizable(false);
		 frame.setLocationRelativeTo(null);
		 frame.setLayout(null);
		 frame.requestFocus();
		 
		 Title = new JLabel("Zombie Shootout");
		 Title.setBounds(70,50,700,80);
		 Title.setFont(Title.getFont().deriveFont((float) 80));
		 frame.add(Title);
		 

		 
		 
		 startbutton = new JButton("Start Game");
		 startbutton.setBounds(200,125,400,75);
		 startbutton.setBackground(new Color(0, 255, 68));
	     startbutton.setFocusPainted(false);
		 startbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6),Color.BLACK)
				               //BorderFactory.createMatteBorder(6,6,6,6,Color.BLACK)
				 );
		 startbutton.addActionListener(new ActionHandler());
		 startbutton.setVisible(true);
		 frame.add(startbutton);
		 
		 
		 shopbutton = new JButton("Shop");
		 shopbutton.setBounds(200,225,400,75);
		 shopbutton.setBackground(new Color(255,255,255));
		 shopbutton.setFocusPainted(false);
		 shopbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 shopbutton.addActionListener(new ActionHandler());
		 shopbutton.setVisible(true);
		 frame.add(shopbutton);
		 
		 
		 
		 settingsbutton = new JButton("Settings");
		 settingsbutton.setBounds(200,325,400,75);
		 settingsbutton.setBackground(new Color(255,255,255));
		 settingsbutton.setFocusPainted(false);
		 settingsbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 settingsbutton.addActionListener(new ActionHandler());
		 settingsbutton.setVisible(true);
		 frame.add(settingsbutton);
		 
		 
	
		 helpbutton = new JButton("Help");
		 helpbutton.setBounds(200,425,175,75);
		 helpbutton.setBackground(new Color(255,255,255));
		 helpbutton.setFocusPainted(false);
		 helpbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 helpbutton.addActionListener(new ActionHandler());
		 helpbutton.setVisible(true);
		 frame.add(helpbutton);
		 
		 
		 quitbutton = new JButton("Quit");
		 quitbutton.setBounds(425,425,175,75);
		 quitbutton.setBackground(new Color(255,30,0));
		 quitbutton.setFocusPainted(false);
		 quitbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 quitbutton.addActionListener(new ActionHandler());
		 quitbutton.setVisible(true);
		 frame.add(quitbutton);
		 
		 /*
		 bg = new JLabel("");
		 bg.setIcon(new ImageIcon("rsc/test.png"));
		 bg.setBounds(0,0,800,600);
		 frame.add(bg);
		 
		
		 draw draw = new draw();
	     draw.setBounds(0,0,800,600); //Größe
	     draw.setVisible(true);
	     frame.add(draw);*/
	     frame.setVisible(true); 
	     

	 }
}
