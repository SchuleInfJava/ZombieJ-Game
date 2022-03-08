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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import MenuUI.Shop;
import MenuUI.ImageLoader;
import MenuUI.Screen;
import Objects.Missile;
import Objects.Player;
import Objects.Zombie;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.*;

import java.awt.BasicStroke;

public class GamePanel extends JPanel {// erben von JPanel
	
	public static JButton continuebutton, shopbutton,helpbutton, startmenubutton,restartbutton,newstartmenubutton;
	public ImageLoader il=new ImageLoader();
	private boolean gameWin = false;//prüft ob gewonnen ist
	private boolean gameEnd = false;//prüft ob das Spiel vorbei ist
	private int money = 0;//Geld
	private boolean spiel = true; //ob spiel pausiert oder nicht
	private boolean schleifenbreak= false; // das die innere for schleife nur einmal läuft
    public static boolean otherJPanel=false; //prüft ob ein anderes JPanel geöffnet wird
	
	private static int wave =0; //Wave
	private boolean wavepaint=false;//Ob eine Nachricht über die Wave gemalt werden soll
	public int wavecounter =5;//prüft wie viele zombies pro Wave getötet werden müssen
	public int wavetimer=100; //prüft wie lange wavepaint gezeigt werden soll
    public boolean wavemodulo=true; //prüft ob alle 5 Wave ein wavepaint entstehen kann
	
	private int zombiedeaths = 0; //prüft wie veiele zombies in der wave getötet wurden
	private int zombiecounter=0; //zählt wieviele zombies insgesamt getötet wurden
	private double zombiewait =0;  //wie lange es dauert bis ein neuer spawnt
	
	

	private Timer t;

	private Player player = null;
	public static List<Missile> missiles;
	private List<Zombie> zombies;
	
	
	
	// Konstruktor
	public GamePanel() {
		
		setFocusable(true);
		setPreferredSize(new Dimension(1000, 800));// feldgröße mit dimension
        setLayout(null);
		initGame(); // Methodeaufruf
		startGame(); // Methodenaufruf
	}

	//Getter / Setter
	
	public Player getPlayer() {
		return player;
	}
	
	public boolean isGameWin() {
		return gameWin;
	}

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


	
	public static int getWave() {
		return wave;
	}
	
	public void setWave(int wave) {
		this.wave=wave;
	}
	
	public static boolean isOtherJPanelOpen() {
		return otherJPanel;
	}
	
	public void setOtherJPanel(boolean otherJPanel) {
		this.otherJPanel=otherJPanel;
	}
	
	
	// initialisieren unser java spiel
	private void initGame() {
		createGameObjects();// erzeugen spieler objekte

		t = new Timer(20, new ActionListener() {// spieltimer oder clock alle 20ms
			@Override
			public void actionPerformed(ActionEvent e) {
				doOnTick(); // Methoden aufruf zeichen der figuren und spielstand zu testen
			}
		});

		this.addKeyListener(new KeyAdapter() { // Abstrakte klasse das wir nicht alles benutzen müssen
			@Override
			public void keyReleased(KeyEvent e) {// wenn losgelassen wird
				

				switch (e.getKeyCode()) {	
				case VK_SPACE:player.stopshootPlayer();
					break;
					
				case VK_S:
				case VK_W:
                case VK_DOWN:
				case VK_UP:player.stopPlayer();
					break;
				
                case VK_A:
				case VK_D:
                case VK_LEFT:
				case VK_RIGHT:player.stopTurningPlayer();
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
					}
				    break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {// wenn gehalten wird
				
				switch (e.getKeyCode()) {
				case VK_M:money += 10;
				    break;
				    
				case VK_SPACE:player.shootPlayer();
				    break;
				    
				case VK_A:    
				case VK_LEFT:player.turnPlayerLeft();
					break;
					
				case VK_D:
				case VK_RIGHT:player.turnPlayerRight();
					break;
					
				case VK_W:
                case VK_UP:player.acceleratePlayer();
					break;
					
                case VK_S:
				case VK_DOWN:player.deceleratePlayer();
					break;
					
				case VK_ESCAPE:if(isSpiel() ==true) {
					createButton();
					buttonListeners();
					 }
					break;
				}
			}
		});
	}
	
	public void createButton() {
		 continuebutton = new JButton("Continue");
		 continuebutton.setBounds(300,230,400,75);
		 continuebutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		 continuebutton.setForeground(Color.BLACK);
		 continuebutton.setBackground(new Color(140,140,140));
		 continuebutton.setFocusPainted(true);
		 continuebutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
		 continuebutton.setVisible(true);
		 add( continuebutton);
		 	 
		 shopbutton = new JButton("Shop");
		 shopbutton.setBounds(300,330,400,75);
		 shopbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		 shopbutton.setForeground(Color.BLACK);
		 shopbutton.setBackground(new Color(140,140,140));
		 shopbutton.setFocusPainted(true);
		 shopbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
	     shopbutton.setVisible(true);
		 add(shopbutton);
		    	 
		 helpbutton = new JButton("Help");
		 helpbutton.setBounds(300,430,400,75);
		 helpbutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		 helpbutton.setForeground(Color.BLACK);
		 helpbutton.setBackground(new Color(140,140,140));
		 helpbutton.setFocusPainted(true);
		 helpbutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
		 helpbutton.setVisible(true);
		 add(helpbutton);
		 	 
		 startmenubutton = new JButton("Startmenu");
		 startmenubutton.setBounds(300,530,400,75);
		 startmenubutton.setFont(new Font(Font.MONOSPACED, Font.BOLD, 50));
		 startmenubutton.setForeground(Color.BLACK);
		 startmenubutton.setBackground(new Color(140,140,140));
		 startmenubutton.setFocusPainted(true);
		 startmenubutton.setBorder(BorderFactory.createStrokeBorder(new BasicStroke(4),Color.BLACK));
		 startmenubutton.setVisible(true);
		 add(startmenubutton);
	}
	
	public void buttonListeners() {
		 continuebutton.addActionListener(new ActionListener() {
			 @Override public void actionPerformed(ActionEvent e) { 
				 continueGame();
			 }
		 });
		 
         shopbutton.addActionListener(new ActionListener() {  
        	 @Override public void actionPerformed(ActionEvent e) {
				 setOtherJPanel(true);
				 GameWindow.gameFrame.setLocationRelativeTo(null);
                 GameWindow.gameFrame.remove(GameWindow.gamePanel);
                 GameWindow.gameFrame.add(GameWindow.shop);
                 GameWindow.gameFrame.revalidate();
                 GameWindow.gameFrame.repaint();
	             GameWindow.gameFrame.setSize(550,670);
	             GameWindow.gameFrame.setLocationRelativeTo(null);	
        	 }
         });
         
         helpbutton.addActionListener(new ActionListener() {
        	 @Override public void actionPerformed(ActionEvent e) {
        		 setOtherJPanel(true);
        		 Screen.screen=false;
		         GameWindow.gameFrame.setLocationRelativeTo(null);
                 GameWindow.gameFrame.remove(GameWindow.gamePanel);
                 GameWindow.gameFrame.add(Screen.help);
                 GameWindow.gameFrame.revalidate();
                 GameWindow.gameFrame.repaint();
                 GameWindow.gameFrame.setSize(820,640);
                 GameWindow.gameFrame.setLocationRelativeTo(null);
        	 }
         });

         startmenubutton.addActionListener(new ActionListener() {
        	 @Override public void actionPerformed(ActionEvent e) {
        		 Screen.frame.setVisible(true);
			     GameWindow.gameFrame.setVisible(false);
        	 }
         });
	}
	
	public void buttonRemove() {
		if(isOtherJPanelOpen()==false) {
		   if(isSpiel()==false) {   
		     this.remove( shopbutton);
		     this.remove( helpbutton);
		     this.remove( startmenubutton);
		     this.remove( continuebutton);
		     setSpiel(true);	
		   }
		}
	}
	

	
	// spielerobjekte erzeugen
	private void createGameObjects() {
		
		player = new Player(new Coordinate(150, 800 / 2 - 60 / 2), 60, 60, Math.toRadians(0), 0);
		player.setLives(20);
		missiles = new LinkedList<>(); // erstellt eine Liste
		zombies = new LinkedList<>(); // erstellt eine Liste
		
		//dummy Zombie damit die schleife gut läuft
		Coordinate zombiec = new Coordinate(0, -100);
		Zombie dummyzombie = new Zombie(zombiec, 60, Math.toRadians(180), 0, player );
		zombies.add(dummyzombie);
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
		 newstartmenubutton.addActionListener(new ActionListener() {
			 @Override public void actionPerformed(ActionEvent e) {
					Screen.frame.setVisible(true);
					GameWindow.gameFrame.setVisible(false);
			}
		});
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

	//Erzeugt Zombies
	public void createZombies() {
		
		Coordinate zombiec = new Coordinate(950, (int) (Math.random() * 800));
		Zombie zombie = new Zombie(zombiec, 60, Math.toRadians(180), 0, player);
		if (zombiewait > 0) {
			zombiewait -= 1;
		} else {
			zombies.add(zombie);

			zombiewait = 100-(1.4*wave);
		}
	}
	


	public void waveCounter() {

		if(zombiedeaths==wavecounter) {
			if(wave<20) {
			wavecounter++;
			}
			wave++;
			wavemodulo=true;
			zombiedeaths =0;
		}
	
		if(wave!=0) {
			if(wavemodulo==true) {
				if(wave%5==0 && wave!=50) {
					wavepaint=true;
					wavemodulo=false;
				}
			}
		}
			
	 if(wave==50) {
			endGame();
			setGameWin(true);
	}
	
	if(wavetimer<=0) {
			wavepaint=false;
			wavetimer=100;
		}
		wavetimer-=1;
	}
	
	// Spielzustand und Zeichnen
	private void doOnTick() {
		createZombies();
		waveCounter();
		buttonRemove();
				
		for (Iterator<Zombie> itZombies = zombies.iterator(); itZombies.hasNext();) {
			//hasNext prüft ob es noch weitere Objekte in der Liste gibt
			
			Zombie dummy = zombies.get(0);
			Zombie zombie = itZombies.next(); // kann man sich das nächste Object aus der liste holen
			
			if(zombie != dummy) { //wenn es nicht der dummy is
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

		//Stats
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 30));
		g.setColor(Color.BLACK);
		g.drawString("Money:" + money + "$", 20, 80);
		g.drawString("Wave:" + wave, 20, 40);
		

		//Missile , Zombies and Player
		for (Missile missile : missiles) {
			missile.paintMe(g);
		}

		for (Zombie zombie : zombies) {
			zombie.paintMe(g);
		}

		player.paintMe(g);
		

		//Weapon Inventory
		g.setColor(new Color(100,100,100,128));
		g.fillRect(300,665,400,135);
		g.setColor(new Color(50,50,50));
		g.drawRect(300,665,400,134);
		g.drawLine(435, 668, 435, 796);
		g.drawImage(il.pistol, 445,665,130,130, null);
		g.drawImage(il.mp, 570,660,130,130, null);
		
		//Supheader
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 22));
		g.setColor(new Color(0));
		g.drawString("Pistol(1)", 448,790);
		g.drawString("MP5(2)", 600,790);
		
		//Boxes for Damage, Range and Speed
		g.drawRect(309,680,115,30);
		g.drawRect(309,720,115,30);
		g.drawRect(309,760,115,30);
		g.setColor(new Color(200,200,200));
		g.fillRect(309,680,115,30);
		g.fillRect(309,720,115,30);
		g.fillRect(309,760,115,30);
			
		//Damage,Range and Speed and Box 
		g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
		g.setColor(Color.BLACK);
		g.drawString("Damage: " + player.damage/2, 316,700);
		
	
		if(player.getWeapon()==1) {
			g.drawRect(440, 670, 125, 125);
			g.drawString("Range: 85" , 316,780);
			g.drawString("Speed:  " + player.missiledirection , 316,740);
		}else {
			g.drawRect(570, 670, 125, 125);
			g.drawString("Range: 35" , 316,780);
			g.drawString("Speed: " + player.missiledirection , 316,740);
		}
	
	
		//Blur Background
		if(!isSpiel() || isGameEnd()) {
			g.setColor(new Color(100,100,100,128));
			g.fillRect(0,0,getWidth(),getHeight());
			
		}
		
		//Options Design
		if(!isSpiel()) {			
			g.setColor(new Color(87,120,150));
			g.fillRect(250,150,500,500);
			
			g.setColor(Color.BLACK);
			g.drawRect(250,150,500,500);
			
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 60));
			g.drawString("Options:",360,200);
			g.drawLine(360,203,660,203);
		}

		//Game end Design
		if (isGameEnd()) {
			if(isGameWin()) {
				g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 160));
				g.setColor(new Color(0,230,0));
				g.drawString("You Win", 150 , 180);
			}else {
				g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 140));
				g.setColor(Color.RED);
				g.drawString("GAME OVER!", 100 , 180);
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

		//Wavepaint
		if(wavepaint==true) {
			g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 80));
			g.setColor(new Color(255,160,0));
			g.drawString("Wave "+wave, 1000/2-130, 800 / 6);	
		}
	}			
}

