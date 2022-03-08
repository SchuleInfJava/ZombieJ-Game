package MenuUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;
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

	public static JButton back,next,quit;
	public ImageLoader il=new ImageLoader();
    public int page;
	
		public Help() {
			page=1;
			this.setFocusable(true);
			this.setPreferredSize(new Dimension(800, 600));
			this.setLayout(null);
			this.setVisible(true);
			this.setBackground(new Color(87,120,150));
			createButton();
			buttonListeners();
			}
						
		public void createButton() {
				    
			quit = new JButton();								
			quit.setBounds(25,25,80,80);
			quit.setBackground(new Color(187,187,187));
			quit.setIcon(il.quit);
			quit.setFocusPainted(true);
			quit.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
			quit.setVisible(true);
		    this.add(quit);
		       
		    back = new JButton("<-Back");								
		    back.setBounds(50,510,125,75);
		    back.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		    back.setForeground(Color.BLACK);
		    back.setBackground(new Color(187,187,187));
		    back.setFocusPainted(true);
		    back.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
		    back.setVisible(true);
		    this.add(back);
		    
		    next = new JButton("Next->");								
		    next.setBounds(625,510,125,75);
		    next.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		    next.setForeground(Color.BLACK);
		    next.setBackground(new Color(187,187,187));
		    next.setFocusPainted(true);
		    next.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3),Color.BLACK));
			next.setVisible(true);
		    this.add(next);      
		}
		
		public void buttonListeners() {
			quit.addActionListener(new ActionListener() {
				 
				 @Override public void actionPerformed(ActionEvent e) {
					if(Screen.screen) {
						page=1;
						 Screen.frame.remove(Screen.help);
						 Screen.frame.add(Screen.startscreen);
						 Screen.frame.revalidate();
						 Screen.frame.repaint();
					}else {
						page=1;
						 GameWindow.gamePanel.setOtherJPanel(false);
						 GameWindow.gameFrame.setLocationRelativeTo(null);
		                 GameWindow.gameFrame.add(GameWindow.gamePanel);
		                 GameWindow.gameFrame.remove(Screen.help);
		                 GameWindow.gameFrame.revalidate();
		                 GameWindow.gameFrame.repaint();
			             GameWindow.gameFrame.setSize(1000,840);
			             GameWindow.gameFrame.setLocationRelativeTo(null);
					}
				}
			});
			
		    back.addActionListener(new ActionListener() {
				 @Override public void actionPerformed(ActionEvent e) {
					 if(page>1) {
						 if(page==2) {
							 add(quit); 
						 }
					 page--;
					 repaint();
					 }
					 }
			});
		    
		    next.addActionListener(new ActionListener() {
				 @Override public void actionPerformed(ActionEvent e) {
					 if(page<5) {
					 remove(quit);
					 page++;
					 repaint();
					 
					 }
				 }
			});
		}
		
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);

			Graphics2D g2d = (Graphics2D) g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			//Box
			g2d.setColor(Color.WHITE);
			g2d.fillRect(25,110,750,390);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(new BasicStroke((float) 3.0));
			g2d.drawRect(25,110,750,390);
			
			//page
	        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
	        g2d.setColor(Color.BLACK);
	        g.drawString("Page: "+page+"/5",285,560);
						
			//Header
	        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
	        g2d.setColor(Color.BLACK);
	        g.drawString("Help",300,80);
	        g2d.setStroke(new BasicStroke(5));
	        g.drawLine(300,83,490,83);
	        
	        //Subheader
	        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
	        g2d.setColor(Color.BLACK);
			switch(page) {
			case 1:g.drawString("Allgemein",230,160); break;
			case 2:g.drawString("Grundlagen",230,160);break;
			case 3:g.drawString("Steuerung",230,160); break;
			case 4:g.drawString("Shop",170,160);      break;
			case 5:g.drawString("Tutorial",255,160);  break;
			}
	 	        
	        //Textfield
	        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 27));
			g.setColor(Color.BLACK);
			switch(page) {
			case 1:
				g.drawString("Zombie-Shootout ist ein 2D Survival-Shooter, ",50,210);
		        g.drawString("aus der Vogelperspektive geschrieben in Java.",50,245);
		        g.drawString("Im Spiel geht es darum so lange wie möglich",50,280);
		        g.drawString("zu überleben und dabei so viele Zombies",50,315);
		        g.drawString("wie möglich zu töten.",50,350);
		        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 28));
		        g.drawString("==>Erreicht man Wave 50 hat man gewonnen.",48,420);
				break;
				
			case 2:
			    g.drawImage(il.map, 200, 180,400,300, null);//Koordinaten und Größe des Bildes
		        g.drawRect(200,180,400,300);
		        g2d.setStroke(new BasicStroke(3));
		        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 27));
		        
		        g.drawString("Counter",45,220);
		        g.drawLine(160,215,205,200);
		        g.drawString("Lifes",50,290);
		        g.drawLine(135,285,250,310);
		        g.drawString("Player",50,360);
		        g.drawLine(150,355,250,330);
		        g.drawString("Inventory",45,430);
		        g.drawLine(195,425,310,450);
		        
		        g.drawString("Fast",640,220);
		        g.drawLine(585,260,635,215);
		        g.drawString("Strong",640,290);
		        g.drawLine(560,290,635,285);
		        g.drawString("bullet",640,360);
		        g.drawLine(430,330,635,355);
		        g.drawString("Zombie",640,430);
		        g.drawLine(560,400,635,425);
		        
		       break;
		       
			case 3:
				g2d.setStroke(new BasicStroke(5));
			    g.drawImage(il.wasd, 170, 180,180,130, null);//Koordinaten und Größe des Bildes
		        g.drawRect(170,180,180,130);
			    g.drawImage(il.arrows, 450, 180,180,130, null);//Koordinaten und Größe des Bildes
		        g.drawRect(450,180,180,130);
		        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 25));
		        g2d.setStroke(new BasicStroke(3));
		        g.drawString("Spieler-Movement:",45,340);
		        g.drawLine(45,343,300,343);
		        g.drawString("Waffe/Schießen:",45,445);
		        g.drawLine(45,448,272,448);
		        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22)); 
		        g.drawString("W/Oben:nach vorne laufen   S/Unten:nach hinten laufen",60,375);
		        g.drawString("A/Links:links drehen       D/Rechts:rechts drehen",60,410);
		        g.drawString("1/2:Primär-Waffe/Sekundär-Waffe  Space:Shoot Esc:Options",30,480);
				break;
				
			case 4:
				g2d.setStroke(new BasicStroke(3));
			    g.drawImage(il.shop, 470, 120,295,370, null);//Koordinaten und Größe des Bildes
		        g.drawRect(470,120,295,370);
		        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22)); 
		        g.drawString("Im Shop kann man die Farbe des ",45,200);
		        g.drawString("Spielers oder der Waffe ändern. ",45,240);
		        g.drawString("Jede Farbe kostet 30$.",45,280);
		        g.drawString("Legende:",45,330);
		        g.drawLine(45,333,145,333);
		        g.setColor(Color.RED);
		        g.drawString("1.",470,220);
		        g.drawString("2.",630,205);
		        g.drawString("3.",470,335);
		        g.drawString("4.",495,370);
		        g.drawString("5.",495,467);
		        g.drawString("6.",610,467);
		        g.setColor(Color.BLACK);
		        g.drawString("1.Vorschau",55,370);
		        g.drawString("2.Kosten",55,410);
		        g.drawString("3.Auswahl",55,450);
		        g.drawString("4.Farbe wählen",220,370);
		        g.drawString("5.Abbruch",220,410);
		        g.drawString("6.Kaufen",220,450);

				break;
				
			case 5:
				g2d.setStroke(new BasicStroke(5));
			    g.drawImage(il.qr, 250, 180,300,300, null);//Koordinaten und Größe des Bildes
		        g.drawRect(250,180,300,300);
				break;
				
			}
		}	
}	
		
