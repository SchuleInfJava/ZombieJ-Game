package Game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class GamePanel extends JPanel {// erben von JPanel
	
	static JButton continuebutton, shopbutton, settingsbutton, startmenubutton;
	private int width = 1000;
	private int height = 800;
	private final Dimension prefSize = new Dimension(width, height);
	private boolean gameOver = false;
	private int money = 0;
	private int wave = 0;
	private int Zombiewait =0;         
	private boolean spiel = true;
	

	private Timer t;

	private Player player = null;
	private List<Missile> missiles;
	private List<Zombie> zombies;
	
	
	
	// Konstruktor
	public GamePanel() {
		setFocusable(true);
		setPreferredSize(prefSize);// feldgröße mit dimension

		initGame(); // Methodeaufruf
		startGame(); // Methodenaufruf
	}

	// abfrage auf was gameover steht
	public boolean isGameOver() {
		return gameOver;
	}

	// gameover auf einen wert setzen
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	public boolean isSpiel() {
		return spiel;
	}

	public void setSpiel(boolean spiel) {
		this.spiel = spiel;
	}
	
	public int getMoney() {
		return money;
	}
	
	public void setMoney(int money) {
		this.money=money;
	}

	// initialisieren unser java spiel
	private void initGame() {

		createGameObjects();// erzeugen spieler objekte

		t = new Timer(20, new ActionListener() {// spieltimer oder clock alle 20ms
			@Override
			public void actionPerformed(ActionEvent e) {
				doOnTick();
				// Methoden aufruf zeichen der figuren und spielstand zu testen

			}
		});

		addKeyListener(new KeyAdapter() { // Abstrakte klasse das wir nicht alles benutzen müssen
			@Override
			public void keyReleased(KeyEvent e) {// wenn losgelassen wird

				switch (e.getKeyCode()) {
				case VK_SPACE:
					if (player.isAbleToShoot()) {
                       missiles.add(player.shoot());
                      }
					break;
                case VK_DOWN:
				case VK_UP:player.stopPlayer();
					break;
					
                case VK_LEFT:
				case VK_RIGHT:player.stopTurningPlayer();
					break;
					
				case VK_S:
				case VK_W:player.stopPlayer();
					break;
					
                case VK_A:
				case VK_D:player.stopTurningPlayer();
					break;
					
				case VK_M:money += 10;
					break;
				case VK_1:player.setWeapon(1);
					break;
				case VK_2:player.setWeapon(2);
					break;
				case VK_ESCAPE:if(isSpiel() ==true) {
					
					setSpiel(false);	
					pauseGame();
						 
						
					 
				}else {
					continueGame();
					setSpiel(true);	
					
					remove( continuebutton);
					remove( shopbutton);
					remove( settingsbutton);
					remove( startmenubutton);
				}
				    break;

				}
			}

			@Override
			public void keyPressed(KeyEvent e) {// wenn gehalten wird
				switch (e.getKeyCode()) {
				case VK_LEFT:player.turnPlayerLeft();
					break;
				case VK_RIGHT:player.turnPlayerRight();
					break;
                case VK_UP:player.acceleratePlayer();
					break;
				case VK_DOWN:player.deceleratePlayer();
					break;
				case VK_A:player.turnPlayerLeft();
					break;
				case VK_D:player.turnPlayerRight();
					break;
                case VK_W:player.acceleratePlayer();
					break;
				case VK_S:player.deceleratePlayer();
					break;
				case VK_ESCAPE:if(isSpiel() ==true) {
					
					 continuebutton = new JButton("Continue");
					 continuebutton.setBounds(300,100,400,75);
					 continuebutton.setBackground(new Color(0, 255, 68));
					 continuebutton.setFocusPainted(false);
					 continuebutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					 continuebutton.addActionListener(new ActionListener() {
						 
						 @Override public void actionPerformed(ActionEvent e) {
							 
								remove( continuebutton);
								remove( shopbutton);
								remove( settingsbutton);
								remove( startmenubutton);
								continueGame();
								setSpiel(true);	

						}
					});
					 continuebutton.setVisible(true);
					 
					 
					shopbutton = new JButton("Shop");
					shopbutton.setBounds(300,200,400,75);
					shopbutton.setBackground(new Color(255, 255, 255));
					shopbutton.setFocusPainted(false);
				    shopbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				    shopbutton.addActionListener(new ActionHandler());
				    shopbutton.setVisible(true);
					
					 
					 settingsbutton = new JButton("Settings");
					 settingsbutton.setBounds(300,300,400,75);
					 settingsbutton.setBackground(new Color(255,255,255));
					 settingsbutton.setFocusPainted(false);
					 settingsbutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					 settingsbutton.addActionListener(new ActionHandler());
					 settingsbutton.setVisible(true);
					 
					 
					 startmenubutton = new JButton("Startmenu");
					 startmenubutton.setBounds(300,400,400,75);
					 startmenubutton.setBackground(new Color(255,30,0));
					 startmenubutton.setFocusPainted(false);
					 startmenubutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					 startmenubutton.addActionListener(new ActionHandler());
					 startmenubutton.setVisible(true);
					 add( continuebutton);
					 add(shopbutton);
					 add(settingsbutton);
					 add(startmenubutton);}
					break;

				}
			}
		});
	}

	/*
	 * 
	 * Pfeiltaste Links/A – Spieler nach links drehen
	 * Pfeiltaste Rechts/D –Spielernach rechts drehen
	 * Pfeiltaste Oben/W – Spieler vorwärts bewegen
	 * Pfeiltaste Unten/S – Spieler rückwärts bewegen
	 * Taste Space -Geschossabgefeurt
	 * Taste k - Money +10
	 * Taste 1 oder 2 - Waffenauswahl
	 * Taste ESC - Pause/Weiter
	 * 
	 */

	// spielerobjekte erzeugen
	private void createGameObjects() {
		if (player == null) {
			player = new Player(new Coordinate(150, 400), 60, 60, Math.toRadians(0), 0);
		}

		initPlayer(); // Methode aufruf
		missiles = new LinkedList<>(); // erstellt eine Liste
		zombies = new LinkedList<>(); // erstellt eine Liste

	}

	// Erstellt Spieler
	private void initPlayer() {
		player.setObjectPosition(new Coordinate(150, height / 2 - player.getHeight() / 2));
		player.setMovingAngle(Math.toRadians(0));
		player.setLives(10);
	}

	// änderung des Spielzustandes mit hilfe des timers
	// starten
	private void startGame() {
		t.start();
	}

	// pausieren
	public void pauseGame() {
		 t.stop();	
	}
	
	// nach dem pausieren wieder starten lassen
	public void continueGame() {
		if (!isGameOver())
			t.start();
	}

	// wenn das spiel neu gestartet wird
	public void restartGame() {
		money = 0;
		wave = 0;
		setGameOver(false);
		createGameObjects();
		startGame();
	}

	// wenn wir verloren haben
	private void endGame() {
		setGameOver(true);
		pauseGame();
	}

	public void createZombies() {

		int zombiesize = 60;

		Coordinate zombiec = new Coordinate(950, (int) (Math.random() * 800));
		Zombie zombie = new Zombie(zombiec, zombiesize, Math.toRadians(180), 0, player);

		if (Zombiewait > 0) {
			Zombiewait -= 1;

		} else {
			zombies.add(zombie);
			Zombiewait = 100;
		}

	}

	// Spielzustand und Zeichnen
	private void doOnTick() {
		createZombies();
		for (Iterator<Zombie> itZombies = zombies.iterator(); itZombies.hasNext();) {
			//hasNext prüft ob es noch weitere Objekte in der Liste gibt
			
			Zombie zombie = itZombies.next(); // kann man sich das nächste Object aus der liste holen
			zombie.makeMove();// Zombie bewegt sich

			if (player.touches(zombie)) {
				if (player.getLives() >= 1) {
					player.setLives(player.getLives() - zombie.getZdamage());
				} if (player.getLives()==0) {
					player.setLives(0);
					endGame();
				}
				itZombies.remove();
			}

			for (Iterator<Missile> itMissiles = missiles.iterator(); itMissiles.hasNext();) {
				// hasNext prüft ob es noch weitere Objekte in der LIste gibt
				
				Missile missile = itMissiles.next(); // kann man sich das nächste Object aus der liste holen
				missile.makeMove();// Geschoss bewegt sich
				
				if (missile.getRange() <= 0) {// ein geschoss geht der treibstoff aus
					itMissiles.remove();
				}

				if (missile.touches(zombie) && missile.getRange() > 1) {
					if (zombie.getLives() > 1) {
						zombie.setPaintStatusBar(true);
						zombie.setLives(zombie.getLives() - player.getDamage());
					} else {
						itZombies.remove();
						money += zombie.getZcash();
					}
					itMissiles.remove();

				}
				
			}

		}

		 // bewegung des Spielers
		player.makeMove();
		repaint();

 }
	

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		g.setColor(Color.BLACK);
		g.drawString("Money:" + money + "$", 20, 80);
		g.drawString("Wave:" + wave, 20, 40);// Schrift +farbe+ text

		for (Missile missile : missiles) {
			missile.paintMe(g);
		}

		for (Zombie zombie : zombies) {
			zombie.paintMe(g);
		}

		player.paintMe(g);

		if (isGameOver()) {
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
			g.setColor(Color.RED);
			g.drawString("GAME OVER!", prefSize.width / 2 - 130, prefSize.height / 5);
		}
		

	}

}
