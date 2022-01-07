package Game;

import javax.swing.JFrame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameWindow extends JFrame {// erben von JFrame

	private final GamePanel gamepanel;// Membervariable vom typ gamepanel

	public GameWindow() {

		this.gamepanel = new GamePanel(); // neues Gamepanel erstellt

		registerWindowListener(); // methoden aufruf

		add(gamepanel); // fügt dem Jframe das panel hinzu
		pack();

		setTitle("ZombieJ-Game");

		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// Windowlistener prüft auf aktion mit dem fenster
	private void registerWindowListener() {
		addWindowListener(new WindowAdapter() { // abstrakte klasse welche nicht alle anweisungen von windowlistener
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
				gamepanel.continueGame(); // hier wird das spiel fortgesetzt
			}
		});
	}

}
