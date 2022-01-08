package Game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class ActionHandler implements ActionListener {
	public 	static Color ColorM;
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == Main.startbutton) {
			new GameWindow();
		
		}
		if(e.getSource() == Main.shopbutton) {
			;
		}
		if(e.getSource() == Main.settingsbutton) {
			new Settings();
		}
		if(e.getSource() == Main.helpbutton) {
			System.out.println("Hallo Spieler;\r\n"
					+ "\r\n"
					+ "Im Spiel \"*hier Name einfügen*\" geht es darum so viele Zombies wie möglich zu vernichten.\r\n"
					+ "\r\n"
					+ "Euch bewegen könnt ihr sowohl über W A S D also auch über die Pfeiltasten.\r\n"
					+ "\r\n"
					+ "Schießen könnt ihr durch betätigen der Leertaste und auf den Tasten 1 und 2 seid ihr in der Lage eure Waffen zu wechseln.\r\n"
					+ "\r\n"
					+ "Für das Töten jedes Zombies bekommt ihr Geld, welches ihr wiederrum im Shop einsetzen könnt, um euch beispielsweise neu Farben zu kaufen.\r\n"
					+ "\r\n"
					+ "");
		}
		if(e.getSource() == Main.quitbutton) {
			System.exit(0);	
		}
		if(e.getSource() == Settings.ProjektilRot) {
			int ColorM = 1;
		}

		if(e.getSource() == Settings.ProjektilGelb) {
			int ColorM = 2	;
				
		if(e.getSource() == Settings.Back) {
			
			//Settings close;	
		
		
		}
		}
		
	}
		
}
	
	
	

