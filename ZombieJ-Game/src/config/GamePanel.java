package config;

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

import MenuUI.ActionHandler;
import Objects.Missile;
import Objects.Player;
import Objects.Zombie;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

public class GamePanel extends JPanel {// erben von JPanel
	
	public static JButton continuebutton, shopbutton, settingsbutton, startmenubutton,newstartmenubutton;
	private int width = 1000;
	private int height = 800;
	private final Dimension prefSize = new Dimension(width, height);
	private boolean gameOver = false;
	private int money = 0;
	private int wave = 0;
	private int zombiedeaths = 0;
	private static int zombiealive = 0;
	private int zombiewait =0;         
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

	public static int getZombiealive() {
		return zombiealive;
	}

	public void setZombiealive(int zombiealive) {
		this.zombiealive = zombiealive;
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

		this.addKeyListener(new KeyAdapter() { // Abstrakte klasse das wir nicht alles benutzen müssen
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
					repaint();
					pauseGame();
						 
						
					 
				}else {
					continueGame();
					setSpiel(true);	
					
					remove( shopbutton);
					remove( settingsbutton);
					remove( startmenubutton);
					
					remove( continuebutton);
					

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
							 
							    remove( shopbutton);
								remove( settingsbutton);
								remove( startmenubutton);
								
								remove( continuebutton);//muss als letztes aufgerufen werden
								

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
					 add(startmenubutton);
					 }
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
		
		Coordinate zombiec = new Coordinate(0, -100);
		Zombie zombie = new Zombie(zombiec, 60, Math.toRadians(180), 0, player );
		zombies.add(zombie);

	}

	// Erstellt Spieler
	private void initPlayer() {
		player.setObjectPosition(new Coordinate(150, height / 2 - player.getHeight() / 2));
		player.setMovingAngle(Math.toRadians(0));
		player.setLives(20);
	}

	// änderung des Spielzustandes mit hilfe des timers
	// starten
	public void startGame() {
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
		//createGameObjects();
		initGame();
		startGame();
	}

	// wenn wir verloren haben
	private void endGame() {
		setGameOver(true);
		pauseGame();
		
		 newstartmenubutton = new JButton("Restart");
		 newstartmenubutton.setBounds(300,300,400,75);
		 newstartmenubutton.setBackground(new Color(255,255,255));
		 newstartmenubutton.setFocusPainted(false);
		 newstartmenubutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 newstartmenubutton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) {
				 
				 
				 remove(newstartmenubutton);
				 remove(startmenubutton);
				 restartGame();

			}
		});
		 newstartmenubutton.setVisible(true);
		 
		 
		 startmenubutton = new JButton("Startmenu");
		 startmenubutton.setBounds(300,400,400,75);
		 startmenubutton.setBackground(new Color(255,30,0));
		 startmenubutton.setFocusPainted(false);
		 startmenubutton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		 startmenubutton.addActionListener(new ActionHandler());
		 startmenubutton.setVisible(true);
		 
		 add(newstartmenubutton);
		 add(startmenubutton);
	}

	public void createZombies() {

		int zombiesize = 60;

		Coordinate zombiec = new Coordinate(950, (int) (Math.random() * 800));
		Zombie zombie = new Zombie(zombiec, zombiesize, Math.toRadians(180), 0, player);

		if (zombiewait > 0) {
			zombiewait -= 1;

		} else {
			zombies.add(zombie);
			setZombiealive(getZombiealive() + 1);
			System.out.println(getZombiealive());
			zombiewait = 100;
		}

	}
	
	public void WaveCounter() {
		int x =5;
		if(zombiedeaths==x) {
			x +=1;
			wave +=1;
			zombiedeaths =0;
		}
	}

	// Spielzustand und Zeichnen
	private void doOnTick() {
		createZombies();
		WaveCounter();
		for (Iterator<Zombie> itZombies = zombies.iterator(); itZombies.hasNext();) {
			//hasNext prüft ob es noch weitere Objekte in der Liste gibt
			
			Zombie dummy = zombies.get(0);
			Zombie zombie = itZombies.next(); // kann man sich das nächste Object aus der liste holen
			
			if(zombie != dummy) {
			zombie.makeMove();// Zombie bewegt sich
			}
			if (player.touches(zombie)) {
				if (player.getLives() > 2) {
					player.setLives(player.getLives() - zombie.getZdamage());
				} else {
					player.setLives(0);
					endGame();
				}
				itZombies.remove();
				setZombiealive(getZombiealive() - 1);
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
						zombiedeaths +=1;
						setZombiealive(getZombiealive() - 1);
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
		
		if(!isSpiel() || isGameOver()) {
			g.setColor(new Color(100,100,100,128));
			g.fillRect(0,0,getWidth(),getHeight());
		}
		

	}

}
