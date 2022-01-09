package Game;
 

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Startscreen {

	static JButton startbutton;
	static JButton shopbutton;
	static JButton settingsbutton;
	static JButton helpbutton;
	static JButton quitbutton;
		   JLabel Title;
	
	
	 public Startscreen() {
	
		 JFrame frame = new JFrame("ZombieJGame");
		 frame.setSize(800,600);
		 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet Prozess falls geschlossen wird
		 frame.setResizable(false);
		 frame.setLocationRelativeTo(null);
		 frame.setLayout(null);
		 frame.requestFocus();
		 
		 
		 Title = new JLabel("*Hier Name einfügen*");
		 Title.setBounds(220,50,400,50);
		 Title.setFont(Title.getFont().deriveFont((float) 35));
		 frame.add(Title);
		 
		 
		 startbutton = new JButton("Start Game");
		 startbutton.setBounds(200,125,400,75);
		 startbutton.setBackground(new Color(0, 255, 68));
	     startbutton.setFocusPainted(false);
		 startbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
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
		 frame.setVisible(true); 
	 }
	


}
