package MenuUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Objects.Missile;
import Objects.Zombie;
import config.GameWindow;

import java.awt.BasicStroke;
import java.awt.Color;


import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Help extends JPanel{

	
	private final Dimension size = new Dimension(800, 600);
	public static JButton Left,Right,Back;
	public JLabel Title,Text1,Text2,Text3,Text4,Text5;
	public ImageLoader icon=new ImageLoader();
	public draw draw = new draw();
	public int page=0;
	
		public Help() {
			this.setFocusable(true);
			this.setPreferredSize(size);
			this.setLayout(null);
			this.setVisible(true);
			Buttoncreator();
			if(page!=0) {
				 remove(Title);
				 remove(Text1);
				 remove(Text2);
				 remove(Text3);
				 remove(Text4);
				 remove(Text5);
				 repaint();
			}
				page1();
	        	
		}
		
		
		
		public void Buttoncreator() {
			
			 Title = new JLabel("Help");
			 Title.setBounds(300,20,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 80));
			 this.add(Title);
	
			  //ZurückButton  
			    
			Back = new JButton("Back");								
			Back.setBounds(25,25,100,75);
			Back.setBackground(new Color(255,255,255));
			Back.setFocusPainted(false);
			Back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Back.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					if(screen.screen) {
						 screen.frame.remove(screen.help);
						 screen.frame.add(screen.startscreen);
						 screen.frame.revalidate();
						 screen.frame.repaint();
					}else {
						 GameWindow.Gameframe.setLocationRelativeTo(null);
		                 GameWindow.Gameframe.remove(screen.help);
		                 GameWindow.Gameframe.add(GameWindow.gamepanel);
		                 GameWindow.Gameframe.revalidate();
		                 GameWindow.Gameframe.repaint();
			             GameWindow.Gameframe.setSize(1000,800);
			             GameWindow.Gameframe.setLocationRelativeTo(null);
					}


				}
			});
			Back.setVisible(true);
		    this.add(Back);
		    
		    
		    Left = new JButton("Left");								
			Left.setBounds(25,500,75,75);
			Left.setBackground(Color.YELLOW);
			Left.setFocusPainted(false);
			Left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Left.addActionListener(new ActionListener() {
				 @Override public void actionPerformed(ActionEvent e) {
					 if(page>1) {
					 remove(Title);
					 remove(draw);
					 page1();
					 add(Back);
					 add(Title);
					 add(Text1);
					 add(Text2);
					 add(Text3);
					 add(Text4);
					 add(Text5);
					 repaint();
					 page=1;
					 }
					 }
			});
			Left.setVisible(true);
		    this.add(Left);
		    
		    Right = new JButton("Right");								
			Right.setBounds(700,500,75,75);
			Right.setBackground(Color.YELLOW);
			Right.setFocusPainted(false);
			Right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Right.addActionListener(new ActionListener() {
				 @Override public void actionPerformed(ActionEvent e) {
					 if(page<2) {
					 remove(Title);
					 page2();
					 remove(Back);
					 remove(Text1);
					 remove(Text2);
					 remove(Text3);
					 remove(Text4);
					 remove(Text5);
					 repaint();
					 page=2;
					 }
				 }	
			});
			Right.setVisible(true);
		    this.add(Right);
		        
		}
		
		public void page1() {
			 Title = new JLabel("<html><u>Allgemein</u></html>");
			 Title.setBounds(270,100,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 50));
			 this.add(Title);
			 
			 Text1= new JLabel("Zombie-Shootout ist ein 2D Survival-Shooter aus");
			 Text1.setBounds(40,120,740,200);
			 Text1.setFont(Text1.getFont().deriveFont((float) 28));
			 this.add(Text1);
			 
			 Text2= new JLabel("der Vogelperspektive geschrieben in Java");
			 Text2.setBounds(40,148,740,200);
			 Text2.setFont(Text2.getFont().deriveFont((float) 28));
			 this.add(Text2);
			 
			 Text3= new JLabel("Im Spiel geht es darum so lange wie möglich zu");
			 Text3.setBounds(40,230,740,200);
			 Text3.setFont(Text3.getFont().deriveFont((float) 28));
			 this.add(Text3);
			 
			 Text4= new JLabel("überleben und dabei so viele Zombies wie möglich");
			 Text4.setBounds(40,258,740,200);
			 Text4.setFont(Text4.getFont().deriveFont((float) 28));
			 this.add(Text4);
			 
			 Text5= new JLabel("zu töten. Errreicht man Wave 50 hat man gewonnen");
			 Text5.setBounds(40,286,740,200);
			 Text5.setFont(Text5.getFont().deriveFont((float) 28));
			 this.add(Text5);
			 
		}
		public void page2() {
			 Title = new JLabel("<html><u>Grundlagen</u></html>");
			 Title.setBounds(270,100,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 50));
			 this.add(Title);	
			 
			 
		     draw.setBounds(200,200,450,350); //Größe
		     draw.setVisible(true);
		     this.add(draw);
		}
		
		public void page3() {
			 Title = new JLabel("<html><u>Steuerung</u></html>");
			 Title.setBounds(270,100,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 50));
			 this.add(Title);		 
		}
		
		public void page4() {
			 Title = new JLabel("<html><u>Shop</u></html>");
			 Title.setBounds(270,100,400,80);
			 Title.setFont(Title.getFont().deriveFont((float) 50));
			 this.add(Title);		 
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			g2d.setStroke(new BasicStroke((float) 3.0));
			g2d.drawRect(25,110,750,380);

		}
		
}	
		
