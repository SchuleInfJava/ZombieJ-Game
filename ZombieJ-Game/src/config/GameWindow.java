package config;

import javax.swing.JFrame;

import MenuUI.Shop;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {// erben von JFrame

	public static GamePanel gamePanel;// Membervariable vom typ gamepanel
	public static Shop shop;
	public static JFrame gameFrame;

	public GameWindow() {


		gameFrame = new JFrame();
		gamePanel = new GamePanel(); // neues Gamepanel erstellt
		shop  = new Shop();

		windowListener(); // methoden aufruf

		gameFrame.add(gamePanel); // fügt dem Jframe das panel hinzu
		gameFrame.pack();
		gameFrame.setTitle("ZombieJ-Game");
        gameFrame.setResizable(false);
		gameFrame.setLocationRelativeTo(null);
		gameFrame.setVisible(true);

	}

	// Windowlistener prüft auf aktion mit dem fenster
	private void windowListener() {
		gameFrame.addWindowListener(new WindowAdapter() { // abstrakte klasse welche nicht alle anweisungen von windowlistener
												// übernimmt
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				gamePanel.pauseGame(); // hier wird das spiel Pausiert
			
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
