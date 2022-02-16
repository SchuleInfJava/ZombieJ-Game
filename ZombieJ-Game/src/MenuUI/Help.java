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

public class Help extends JPanel{

	
	private final Dimension size = new Dimension(800, 600);
	public static JButton ProjektilGelb,ProjektilRot,Back;
	public JLabel Title;
	
		public Help() {
		
			this.setFocusable(true);
			this.setPreferredSize(size);
			this.setLayout(null);
			this.setVisible(true);
			Buttoncreator();
			
		}
		
		
		
		public void Buttoncreator() {
			
			 Title = new JLabel("Help");
			 Title.setBounds(250,50,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 80));
			 this.add(Title);
			
			    
			
		
			  //ZurückButton  
			    
			Back = new JButton("<---	Back");								
			Back.setBounds(25,450,100,75);
			Back.setBackground(new Color(255,255,255));
			Back.setFocusPainted(false);
			Back.setBorder(BorderFactory.createMatteBorder(6,6,6,6,Color.BLACK));
			Back.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					 
					 screen.frame.remove(screen.help);
					 screen.frame.add(screen.startscreen);
					 screen.frame.revalidate();
					 screen.frame.repaint();

				}
			});
			
			
		    Back.setVisible(true);
		    this.add(Back);
			    
		}
}
