package MenuUI;

import java.awt.Dimension;
import java.awt.Font;
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
		int font=40;
		int stroke=4;

		 startbutton = new JButton("Start");
         startbutton.setBounds(250,260,300,70);
         startbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, font));
         startbutton.setForeground(Color.BLACK);
         startbutton.setBackground(new Color(187,187,187));
         startbutton.setFocusPainted(false);
         startbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(stroke),Color.BLACK));
         startbutton.addActionListener(new ActionHandler());
         startbutton.setVisible(true);
         this.add(startbutton);
		 
		 
         shopbutton = new JButton("Shop");
         shopbutton.setBounds(250,340,300,70);
         shopbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, font));
         shopbutton.setForeground(Color.BLACK);
         shopbutton.setBackground(new Color(187,187,187));
         shopbutton.setFocusPainted(false);
         shopbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(stroke),Color.BLACK));
         shopbutton.addActionListener(new ActionHandler());
         shopbutton.setVisible(true);
         this.add(shopbutton);
		 
		 
/*		 
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
	*/	 
		 
	
         helpbutton = new JButton("Help");
         helpbutton.setBounds(250,420,300,70);
         helpbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, font));
         helpbutton.setForeground(Color.BLACK);
         helpbutton.setBackground(new Color(187,187,187));
         helpbutton.setFocusPainted(false);
         helpbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(stroke),Color.BLACK));
         helpbutton.addActionListener(new ActionListener() {

             @Override public void actionPerformed(ActionEvent e) {

                 screen.frame.remove(screen.startscreen);
                 screen.frame.add(screen.help);
                 screen.frame.revalidate();
                 screen.frame.repaint();
            }
        });
         helpbutton.setVisible(true);
         this.add(helpbutton);
		 
		 
         quitbutton = new JButton("Quit");
         quitbutton.setBounds(250,500,300,70);
         quitbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, font));
         quitbutton.setForeground(Color.BLACK);
         quitbutton.setBackground(new Color(255,101,30));
         quitbutton.setFocusPainted(false);
         quitbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(stroke),Color.BLACK));
         quitbutton.addActionListener(new ActionHandler());
         quitbutton.setVisible(true);
         this.add(quitbutton);
         
         Title = new JLabel("");
         Title.setIcon(new ImageIcon("rsc/titleGame.png"));
         Title.setBounds(60,50,600,300);
         this.add(Title);
         
         bg = new JLabel("");
         bg.setIcon(new ImageIcon("rsc/zombiegame_background.png"));
         bg.setBounds(0,0,800,600);
         this.add(bg);
         

		 
		 /*
		 draw draw = new draw();
	     draw.setBounds(0,0,800,600); //Größe
	     draw.setVisible(true);
	     this.add(draw);*/
		  
	}
}
