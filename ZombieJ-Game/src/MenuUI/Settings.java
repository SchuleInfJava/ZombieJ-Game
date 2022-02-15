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

public class Settings extends JPanel {

	private final Dimension size = new Dimension(800, 600);
	public static JButton ProjektilGelb,ProjektilRot,Back;
	public JLabel Title;
	
		public Settings() {
		
			this.setFocusable(true);
			this.setPreferredSize(size);
			this.setLayout(null);
			this.setVisible(true);
			Buttoncreator();
			
		}
		
		
		public void Buttoncreator() {
			 Title = new JLabel("Settings");
			 Title.setBounds(250,50,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 80));
			 this.add(Title);
			
		
			//Projektilfarbe Rot
			
		
			ProjektilRot = new JButton("Projektil = Rot");
			ProjektilRot.setBounds(200,150,100,75);
			ProjektilRot.setBackground(new Color(255, 30, 0));
			ProjektilRot.setFocusPainted(false);
			ProjektilRot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			ProjektilRot.addActionListener(new ActionHandler());
			ProjektilRot.setVisible(true);
			this.add(ProjektilRot);	
			
			
			 //Projektilfarbe Gelb
			 
			 ProjektilGelb = new JButton("Projektil = Gelb");
			 ProjektilGelb.setBounds(400,150,100,75);
			 ProjektilGelb.setBackground(new Color(229, 255, 0));
			 ProjektilGelb.setFocusPainted(false);
			 ProjektilGelb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			 ProjektilGelb.addActionListener(new ActionHandler());
			 ProjektilGelb.setVisible(true);
			 this.add(ProjektilGelb);
			    
			
		
			  //ZurückButton  
			    
			Back = new JButton("<---	Back");								
			Back.setBounds(25,450,100,75);
			Back.setBackground(new Color(255,255,255));
			Back.setFocusPainted(false);
			Back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Back.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					 
					 screen.frame.remove(screen.settings);
					 screen.frame.add(screen.startscreen);
					 screen.frame.revalidate();
					 screen.frame.repaint();

				}
			});
			
			
		    Back.setVisible(true);
		    this.add(Back);
			    
		}
}
