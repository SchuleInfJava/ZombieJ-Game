package MenuUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import config.GamePanel;
import config.GameWindow;

public class ActionHandler implements ActionListener {

	public static Color ColorM;
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
		
		  //Button vom Startscreen
		 
		if (e.getSource() == Startscreen.startbutton) {
			new GameWindow();
			screen.frame.setVisible(false);
		}

		if (e.getSource() == Startscreen.shopbutton) {
			;
		}

		if (e.getSource() == Startscreen.settingsbutton) {
			new Settings();
		}

		if (e.getSource() == Startscreen.helpbutton) {
			System.out.println("Hallo Spieler;\r\n" + "\r\n"
					+ "Im Spiel \"*hier Name einfügen*\" geht es darum so viele Zombies wie möglich zu vernichten.\r\n"
					+ "\r\n" + "Euch bewegen könnt ihr sowohl über W A S D also auch über die Pfeiltasten.\r\n" + "\r\n"
					+ "Schießen könnt ihr durch betätigen der Leertaste und auf den Tasten 1 und 2 seid ihr in der Lage eure Waffen zu wechseln.\r\n"
					+ "\r\n"
					+ "Für das Töten jedes Zombies bekommt ihr Geld, welches ihr wiederrum im Shop einsetzen könnt, um euch beispielsweise neu Farben zu kaufen.\r\n"
					+ "\r\n" + "");
		}
		if (e.getSource() == Startscreen.quitbutton) {
			System.exit(0);
		}

		
		  //Button aus wenn spiel Pausiert ist
		 
		if (e.getSource() == GamePanel.continuebutton) {
			
			//GamePanel.continueGame();
			//GamePanel.setSpiel(true);
		}

		if (e.getSource() == GamePanel.shopbutton) {

		}

		if (e.getSource() == GamePanel.settingsbutton) {
			new Settings();
		}

		if (e.getSource() == GamePanel.startmenubutton) {
			screen.frame.setVisible(true);
			GameWindow.Gameframe.setVisible(false);
		}

		
		 // Button aus Settings
		 

		if (e.getSource() == Settings.ProjektilRot) {
			int ColorM = 1;
		}

		if (e.getSource() == Settings.ProjektilGelb) {
			int ColorM = 2;
		}

		if (e.getSource() == Settings.Back) {
			// Settings close;
		}

	}
}
