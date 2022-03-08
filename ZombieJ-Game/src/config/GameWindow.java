package config;

import javax.swing.JFrame;

import MenuUI.Shop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {//Erben von JFrame

	public static GamePanel gamePanel;//Membervariable vom typ gamepanel
	public static Shop shop;
	public static JFrame gameFrame;

	public GameWindow() {

		gameFrame = new JFrame();
		gamePanel = new GamePanel(); //neues Gamepanel erstellt
		shop  = new Shop();

		windowListener(); //Methodenaufruf

		gameFrame.add(gamePanel); //f�gt dem Jframe das Panel hinzu
		gameFrame.pack();
		gameFrame.setTitle("ZombieJ-Game");
        gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);

	}

	//Windowlistener pr�ft auf Aktion mit dem Fenster
	private void windowListener() {
		gameFrame.addWindowListener(new WindowAdapter() { //abstrakte Klasse welche nicht alle Anweisungen vom Windowlistener �bernimmt
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				gamePanel.pauseGame(); //Spiel pausiert
			
			}

			@Override
			public void windowActivated(WindowEvent e) {
				if(gamePanel.isOtherJPanelOpen()==false) {
					gamePanel.continueGame();
				}			
			}
		});
	}
}
