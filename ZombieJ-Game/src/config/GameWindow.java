package config;

import javax.swing.JFrame;

import MenuUI.ChoosePlayerColor;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {// erben von JFrame

	public static GamePanel gamepanel;// Membervariable vom typ gamepanel
	public static ChoosePlayerColor choosePlayerColor;
	public static JFrame Gameframe;

	public GameWindow() {


		Gameframe = new JFrame();
		this.gamepanel = new GamePanel(); // neues Gamepanel erstellt
		choosePlayerColor  = new ChoosePlayerColor();

		registerWindowListener(); // methoden aufruf

		Gameframe.add(gamepanel); // fügt dem Jframe das panel hinzu

		Gameframe.pack();

		Gameframe.setTitle("ZombieJ-Game");

		
		Gameframe.setResizable(false);
		Gameframe.setLocationRelativeTo(null);
		Gameframe.setVisible(true);

	}

	// Windowlistener prüft auf aktion mit dem fenster
	private void registerWindowListener() {
		Gameframe.addWindowListener(new WindowAdapter() { // abstrakte klasse welche nicht alle anweisungen von windowlistener
												// übernimmt
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				gamepanel.pauseGame(); // hier wird das spiel Pausiert
			
			}

			@Override
			public void windowActivated(WindowEvent e) {
				gamepanel.continueGame();
			
			}
		});
	}

}
