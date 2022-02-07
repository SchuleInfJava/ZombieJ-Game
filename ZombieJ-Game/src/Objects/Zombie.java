	package Objects;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

import config.Coordinate;

public class Zombie extends Player {

	private Player player;
	private int zombieart = 1;
	private Color zombieColor = new Color(0,160,0);
	private int zdamage = 1;
	private int zcash=2;

	public Zombie(Coordinate position, double size, double movingAngle, double movingDistance, Player player) {
		super(position, size, size, movingAngle, movingDistance);
		this.player = player;
		setTurningVelocity(TURNING_VELOCITY * 10);
		ZombieArt();
		//setDrivingVelocity(DRIVING_VELOCIY / 2);

		//setLives(zombielives);
		//setLivesStart(zombielives);

		setPaintStatusBar(false);

		acceleratePlayer();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getZombieart() {
		return zombieart;
	}

	public void setZombieart(int zombieart) {
		this.zombieart = zombieart;
	}

	public int getZdamage() {
		return zdamage;
	}

	public void setZdamage(int zdamage) {
		this.zdamage = zdamage;
	}

	public int getZcash() {
		return zcash;
	}

	public void setZcash(int zcash) {
		this.zcash = zcash;
	}

	@Override
	public void makeMove() {

		turnToPlayer();

		super.makeMove();
	}

	public void turnToPlayer() {

		// Die Position des Mittelpunkts des Spielers
		double playerCenterX = player.getObjectPosition().getX() + player.getWidth() / 2;
		double playerCenterY = player.getObjectPosition().getY() + player.getHeight() / 2;

		// Die Position des Mittelpunkts des Zombies
		double ZombieCenterX = getObjectPosition().getX() + getWidth() / 2;
		double ZombieCenterY = getObjectPosition().getY() + getHeight() / 2;

		// Berechnung des Winkels vom Spieler zum Zombie
		double x = playerCenterX - ZombieCenterX;
		double y = playerCenterY - ZombieCenterY;
		double angleToPlayer = Math.atan2(y, x);
		if (angleToPlayer < 0)
			angleToPlayer = angleToPlayer + 2 * Math.PI;

		setMovingAngle(angleToPlayer);
	}
	
	public void Wavesystem() {
		
	}
	
	public void ZombieArt() {
		int wert =(int)((Math.random()*10)+1);
		switch (wert) {
		    case 1:
			case 2:
				setZombieart(2);
				break;
			case 3:
				setZombieart(3);
				break;
			default:
				setZombieart(1);
		}
		
		switch (getZombieart()) {
		case 1:
			zombieColor = new Color(0,160,0);//Green
			setZdamage(2);
			setZcash(1);
			setDrivingVelocity(DRIVING_VELOCIY / 1.5);
			setLives(3);
			setLivesStart(3);
			break;
			
		case 2:
			zombieColor = new Color(255,165,0);//Orange
			setZdamage(4);
			setZcash(3);
			setDrivingVelocity(DRIVING_VELOCIY / 3);
			setLives(7);
			setLivesStart(7);
			
			break;
		case 3:
			zombieColor = Color.YELLOW;
			setZdamage(2);
			setZcash(2);
			setDrivingVelocity(DRIVING_VELOCIY * 1.5);
			setLives(1);
			setLivesStart(1);
			
			break;
		}
	}
	
	@Override
	public void paintMe(java.awt.Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (isPaintStatusBar()) {
			paintStatusBars(g2d);// zeichnet die Lebensleiste
		}
		paintZombie(g2d); // zeichnet den Zombie

	}

	private void paintZombie(Graphics2D g2d) {

		// Zombie
		RoundRectangle2D player = new RoundRectangle2D.Double(getObjectPosition().getX(), getObjectPosition().getY(),
				getWidth(), getHeight(), 360, 360);

		Line2D armRechts = new Line2D.Double(getObjectPosition().getX() + getWidth() * 0.6,
				getObjectPosition().getY() + getHeight() * 0.99, getObjectPosition().getX() + getWidth() * 1.5,
				getObjectPosition().getY() + getHeight() * 0.68 + getHeight() * 0.14);

		Line2D armLinks = new Line2D.Double(getObjectPosition().getX() + getWidth() * 0.6,
				getObjectPosition().getY() + getHeight() * 0.01, getObjectPosition().getX() + getWidth() * 1.5,
				getObjectPosition().getY() + getHeight() * 0.32 - getHeight() * 0.14);

		AffineTransform transform = new AffineTransform();
		transform.rotate(getMovingAngle(), player.getCenterX(), player.getCenterY()); 
		// rotieren es um den bewgungswinkel mit dem ankerpunkt in der mitte des panzers

		// Zeichnen vom Zombie gedreht
		g2d.setColor(zombieColor);
		Shape transformed = transform.createTransformedShape(player);
		g2d.fill(transformed);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke((float) 4));
		g2d.draw(transformed);

		// Zeichnen von Armen
		transformed = transform.createTransformedShape(armLinks);
		g2d.draw(transformed);
		transformed = transform.createTransformedShape(armRechts);
		g2d.draw(transformed);

		setTransformedPlayer(transformed);// speichern wir in der Variabel oben für eine saubere collisionsabfrage

	}

	private void paintStatusBars(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke((float) 2));
		double barOffsetY = getHeight() * 0.4;

		// paint frame
		g2d.setColor(Color.GRAY);
		RoundRectangle2D livesBarFrame = new RoundRectangle2D.Double(Math.round(getObjectPosition().getX()) - 1,
				Math.round(getObjectPosition().getY() - barOffsetY) - 1, getWidth() + 1, 6, 0, 0);
		g2d.draw(livesBarFrame);
		if (getLives() > 1) {
			g2d.setColor(Color.GREEN);
		} else {
			g2d.setColor(Color.RED);
		}

		// paint bar
		RoundRectangle2D liveBar = new RoundRectangle2D.Double(Math.round(getObjectPosition().getX()),
				Math.round(getObjectPosition().getY() - barOffsetY), getWidth() / getLivesStart() * (getLives()), 5, 0,
				0);
		g2d.fill(liveBar);

	}
}
