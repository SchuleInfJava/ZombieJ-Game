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

import java.awt.BasicStroke;

public class GamePanel extends JPanel {// erben von JPanel
	
	public static JButton continuebutton, shopbutton, settingsbutton, startmenubutton,restartbutton,newstartmenubutton;
	private int width = 1000;
	private int height = 800;
	private final Dimension prefSize = new Dimension(width, height);
	private boolean gameWin = false;
	private boolean gameEnd = false;
	private int money = 0;
	private static int wave = 0;
	private boolean wavepaint=false;
	private int zombiedeaths = 0;
	private int zombiecounter=0;
	private static int zombiealive = 0;
	private int zombiewait =0;         
	private boolean spiel = true;
	private boolean schleifenbreak= false;
	public int wavecounter =5;
	public int timer=100;
    public boolean modulo=true;
	

	private Timer t;

	private Player player = null;
	public static List<Missile> missiles;
	private List<Zombie> zombies;
	
	
	
	// Konstruktor
	public GamePanel() {
		setFocusable(true);
		setPreferredSize(prefSize);// feldgröße mit dimension
        setBackground(getBackground());
		initGame(); // Methodeaufruf
		startGame(); // Methodenaufruf
	}

	// abfrage auf was gameover steht
	public boolean isGameWin() {
		return gameWin;
	}

	// gameover auf einen wert setzen
	public void setGameWin(boolean gameWin) {
		this.gameWin = gameWin;
	}
	
	public boolean isGameEnd() {
		return gameEnd;
	}

	public void setGameEnd(boolean gameEnd) {
		this.gameEnd = gameEnd;
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
	
	public static int getWave() {
		return wave;
	}
	
	public void setWave(int wave) {
		this.wave=wave;
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
				case VK_SPACE:player.stopshootPlayer();
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
					Buttonremove();
					}
				    break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {// wenn gehalten wird
				
				switch (e.getKeyCode()) {
				
				case VK_SPACE:player.shootPlayer();
				    break;
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
					 continuebutton.setBounds(300,230,400,75);
					 continuebutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
					 continuebutton.setForeground(Color.BLACK);
					 continuebutton.setBackground(new Color(140,140,140));
					 continuebutton.setFocusPainted(true);
					 continuebutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
					 continuebutton.addActionListener(new ActionListener() {
						 
						 @Override public void actionPerformed(ActionEvent e) {
							 
							 Buttonremove();	

						}
					});
					continuebutton.setVisible(true);
					 
					 
					shopbutton = new JButton("Shop");
					shopbutton.setBounds(300,330,400,75);
					shopbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
					shopbutton.setForeground(Color.BLACK);
					shopbutton.setBackground(new Color(140,140,140));
					shopbutton.setFocusPainted(true);
					shopbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
				    shopbutton.addActionListener(new ActionHandler());
				    shopbutton.setVisible(true);
					
				    	 
					 settingsbutton = new JButton("Settings");
					 settingsbutton.setBounds(300,430,400,75);
					 settingsbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
					 settingsbutton.setForeground(Color.BLACK);
					 settingsbutton.setBackground(new Color(140,140,140));
					 settingsbutton.setFocusPainted(true);
					 settingsbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
					 settingsbutton.addActionListener(new ActionHandler());
					 settingsbutton.setVisible(true);
					 
					 
					 startmenubutton = new JButton("Startmenu");
					 startmenubutton.setBounds(300,530,400,75);
					 startmenubutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
					 startmenubutton.setForeground(Color.BLACK);
					 startmenubutton.setBackground(new Color(140,140,140));
					 startmenubutton.setFocusPainted(true);
					 startmenubutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
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

	public void Buttonremove() {
		continueGame();
		setSpiel(true);	
		
		this.remove( shopbutton);
		this.remove( settingsbutton);
		this.remove( startmenubutton);
		this.remove( continuebutton);
	}
	
	public void WindowButtonRemove() {
		if(isSpiel()==false) {
		this.remove( shopbutton);
		this.remove( settingsbutton);
		this.remove( startmenubutton);
		this.remove( continuebutton);
		setSpiel(true);	
		}
	}
	

	
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
		if (!isGameEnd())
			t.start();
	}

	// wenn das spiel neu gestartet wird
	public void restartGame() {
		player=null;
		money = 0;
		wave = 0;
		setGameEnd(false);
		setGameWin(false);
		createGameObjects();
		startGame();
	}

	// wenn wir verloren haben
	private void endGame() {
		
		 setGameEnd(true);
		 pauseGame();

		
		 newstartmenubutton = new JButton("Startmenu");
		 newstartmenubutton.setBounds(300,650,400,75);
		 newstartmenubutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
         newstartmenubutton.setForeground(Color.BLACK);
         newstartmenubutton.setBackground(new Color(140,140,140));
         newstartmenubutton.setFocusPainted(true);
         newstartmenubutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
		 newstartmenubutton.addActionListener(new ActionHandler());
		 newstartmenubutton.setVisible(true);
		 add(newstartmenubutton);
		
		 restartbutton = new JButton("Restart");
		 restartbutton.setBounds(300,550,400,75);
		 restartbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
         restartbutton.setForeground(Color.BLACK);
         restartbutton.setBackground(new Color(140,140,140));
         restartbutton.setFocusPainted(true);
         restartbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
		 restartbutton.addActionListener(new ActionListener() {
			 
			 @Override public void actionPerformed(ActionEvent e) {
				 
				 remove(newstartmenubutton);
				 remove(restartbutton);
				 
				 restartGame();

			}
		});
		 restartbutton.setVisible(true);
		 add(restartbutton);

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
			zombiewait = 100-(2*wave);
		}

	}
	

	public void WaveCounter() {

		if(zombiedeaths==wavecounter) {
			if(wave<20) {
			wavecounter++;
			}
			wave++;
			modulo=true;
			zombiedeaths =0;
		}
		
		if(wave!=0) {
			if(modulo==true) {
				if(wave%5==0 && wave!=50) {
					wavepaint=true;
					modulo=false;
				}
			}
		}
		
		if(wave==50) {
			endGame();
			setGameWin(true);
		}



		
		if(timer<=0) {
			wavepaint=false;
			timer=100;
		}
		timer-=1;
	}

	// Spielzustand und Zeichnen
	private void doOnTick() {
		createZombies();
		WaveCounter();
		WindowButtonRemove();
		
		for (Iterator<Zombie> itZombies = zombies.iterator(); itZombies.hasNext();) {
			//hasNext prüft ob es noch weitere Objekte in der Liste gibt
			
			Zombie dummy = zombies.get(0);
			Zombie zombie = itZombies.next(); // kann man sich das nächste Object aus der liste holen
			
			if(zombie != dummy) {
			zombie.makeMove();// Zombie bewegt sich
			}
			if (player.touches(zombie)) {
				if (player.getLives()- zombie.getZdamage()>0 ) {
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
				 if(schleifenbreak==false) {
				missile.makeMove();// Geschoss bewegt sich
				 }
				if (missile.getRange() <= 0) {// ein geschoss geht der treibstoff aus
					itMissiles.remove();
				}
				 
				if (missile.touches(zombie) && missile.getRange() > 1) {
					if (zombie.getLives() - player.getDamage()>0) {
						zombie.setPaintStatusBar(true);
						zombie.setLives(zombie.getLives() - player.getDamage());
					} else {
						itZombies.remove();
						zombiedeaths +=1;
						zombiecounter+=1;
						setZombiealive(getZombiealive() - 1);
						money += zombie.getZcash();
					}
					itMissiles.remove();

				}
				
			}
			schleifenbreak=true;
			
		
		}

		schleifenbreak=false;
		player.makeMove();// bewegung des Spielers
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
		

		
		if(!isSpiel() || isGameEnd()) {
			g.setColor(new Color(100,100,100,128));
			g.fillRect(0,0,getWidth(),getHeight());
		}
		
		if(!isSpiel()) {			
			g.setColor(new Color(87,120,150));
			g.fillRect(250,150,500,500);
			
			g.setColor(Color.BLACK);
			g.drawRect(250,150,500,500);
			
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
			g.drawString("Options:",360,200);
			g.drawLine(360,203,660,203);
		}

		if (isGameEnd()) {
			if(isGameWin()) {
				g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 160));
				g.setColor(new Color(0,230,0));
				g.drawString("You Win", 150 , 220);
			}else {
				g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 140));
				g.setColor(Color.RED);
				g.drawString("GAME OVER!", 100 , 220);
			}
			g.setColor(new Color(87,120,150));
			g.fillRect(255,235,485,525);
			
			g.setColor(Color.BLACK);
			g.drawRect(255,235,485,525);
			
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 100));
			g.drawString("Stats", 350, 310);
			g.drawLine(350,313,650,313);
			
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 70));
			g.drawString("Money: "+ money, 330, 380);
			g.drawString("Kills: "+ zombiecounter, 330 , 450);
			g.drawString("Wave:  "+ wave, 330 , 520);
		}

		if(wavepaint==true) {
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
			g.setColor(new Color(255,160,0));
			g.drawString("Wave "+wave, prefSize.width/2-130, prefSize.height / 6);
		}
		
		
		/*
		 *     Adrians Code: für dich alles lösbar mit der paintcomponent methode 
		 * 
		 */
		
		if(player.getWeapon()==1) {
			
		}else {
			
		}

	}

}
