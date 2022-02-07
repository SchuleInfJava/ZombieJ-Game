package MenuUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Settings extends ActionHandler {

	static JButton ProjektilGelb;
	static JButton ProjektilRot;
	static JButton Back;
		public Settings() {
		
			// Settingsfenster wird erzeugt
			
			JFrame SettingsWindow = new JFrame("Settings");
			SettingsWindow.setSize(800,600);
			SettingsWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //beendet Prozess falls geschlossen wird
			SettingsWindow.setResizable(false);
			SettingsWindow.setLocationRelativeTo(null);
			SettingsWindow.setLayout(null);
			SettingsWindow.requestFocus();
			
		
			//Projektilfarbe Rot
			
		
			ProjektilRot = new JButton("Projektil = Rot");
			ProjektilRot.setBounds(200,125,100,75);
			ProjektilRot.setBackground(new Color(255, 30, 0));
			ProjektilRot.setFocusPainted(false);
			ProjektilRot.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			ProjektilRot.addActionListener(new ActionHandler());
			ProjektilRot.setVisible(true);
			 SettingsWindow.add(ProjektilRot);	
			
			
			 //Projektilfarbe Gelb
			 
			 ProjektilGelb = new JButton("Projektil = Gelb");
			 ProjektilGelb.setBounds(400,125,100,75);
			 ProjektilGelb.setBackground(new Color(229, 255, 0));
			 ProjektilGelb.setFocusPainted(false);
			 ProjektilGelb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			 ProjektilGelb.addActionListener(new ActionHandler());
			 ProjektilGelb.setVisible(true);
				 SettingsWindow.add(ProjektilGelb);
			    SettingsWindow.setVisible(true);
			
		
			  //ZurückButton  
			    
			Back = new JButton("<---	Back");								
			Back.setBounds(25,450,100,75);
			Back.setBackground(new Color(255,255,255));
			Back.setFocusPainted(false);
			Back.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			Back.addActionListener(new ActionListener() {
				@Override											
				public void actionPerformed(ActionEvent e) {					//Settings werden geschlossen
				SettingsWindow.setVisible(false);	
				}
				
			});
			
			
			Back.setVisible(true);
				 SettingsWindow.add(Back);
			    SettingsWindow.setVisible(true);
				    
			  
		
		}
}
