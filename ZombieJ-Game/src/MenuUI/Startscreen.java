package MenuUI;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import java.awt.BasicStroke;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Startscreen extends JPanel{
	
	private final Dimension size = new Dimension(800, 600);
	public static JButton startbutton, shopbutton, settingsbutton, helpbutton, quitbutton, button;
	public JLabel Title, bg;
	
	public Startscreen() {
	this.setFocusable(true);
	this.setPreferredSize(size);
	this.setLayout(null);
	this.setVisible(true);
	Buttoncreator();
	
	}
	
	public void Buttoncreator() {
		
		 Title = new JLabel("Zombie Shootout");
		 Title.setBounds(70,50,700,80);
		 Title.setFont(Title.getFont().deriveFont((float) 80));
		 this.add(Title);
		 

		 
		 
		 startbutton = new JButton("Start Game");
		 startbutton.setBounds(200,125,400,75);
		 startbutton.setBackground(new Color(0, 255, 68));
	     startbutton.setFocusPainted(false);
		 startbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(6),Color.BLACK)
				               //BorderFactory.createMatteBorder(6,6,6,6,Color.BLACK)
				 );
		 startbutton.addActionListener(new ActionHandler());
		 startbutton.setVisible(true);
		 this.add(startbutton);
		 
		 
		 shopbutton = new JButton("Shop");
		 shopbutton.setBounds(200,225,400,75);
		 shopbutton.setBackground(new Color(255,255,255));
		 shopbutton.setFocusPainted(false);
		 shopbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 shopbutton.addActionListener(new ActionHandler());
		 shopbutton.setVisible(true);
		 this.add(shopbutton);
		 
		 
		 
		 settingsbutton = new JButton("Settings");
		 settingsbutton.setBounds(200,325,400,75);
		 settingsbutton.setBackground(new Color(255,255,255));
		 settingsbutton.setFocusPainted(false);
		 settingsbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 settingsbutton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) {

				 screen.frame.remove(screen.startscreen);
				 screen.frame.add(screen.settings);
				 screen.frame.revalidate();
				 screen.frame.repaint();
			}
		});
		 settingsbutton.setVisible(true);
		 this.add(settingsbutton);
		 
		 
	
		 helpbutton = new JButton("Help");
		 helpbutton.setBounds(200,425,175,75);
		 helpbutton.setBackground(new Color(255,255,255));
		 helpbutton.setFocusPainted(false);
		 helpbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 helpbutton.addActionListener(new ActionHandler());
		 helpbutton.setVisible(true);
		 this.add(helpbutton);
		 
		 
		 quitbutton = new JButton("Quit");
		 quitbutton.setBounds(425,425,175,75);
		 quitbutton.setBackground(new Color(255,30,0));
		 quitbutton.setFocusPainted(false);
		 quitbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 quitbutton.addActionListener(new ActionHandler());
		 quitbutton.setVisible(true);
		 this.add(quitbutton);
		 
		 /*
		 bg = new JLabel("");
		 bg.setIcon(new ImageIcon("rsc/test.png"));
		 bg.setBounds(0,0,800,600);
		 this.add(bg);
		 
		
		 draw draw = new draw();
	     draw.setBounds(0,0,800,600); //Größe
	     draw.setVisible(true);
	     this.add(draw);*/
		  
	}
}
