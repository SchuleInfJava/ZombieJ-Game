package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import config.Coordinate;
import config.GamePanel;

import java.awt.geom.Line2D;
import java.awt.BasicStroke;

public class Player extends GameObject {

	//Variablen
	public static final double TURNING_VELOCITY = 0.2; //Drehgeschwindigkeit 
	public static final double DRIVING_VELOCIY = 4.00;//Fahrgeschwindigkeit 

	private Shape  transformedPlayer = new RoundRectangle2D.Double(); //enth�lt die transformierte Form des Spielers
														

	private double turningVelocity = TURNING_VELOCITY;//speichert den aktuellen Wert der Drehgeschwindigkeit
	private double drivingVelocity = DRIVING_VELOCIY; //speichert den aktuellen Wert der Fahrgeschwindigkeit

	private double deltaMovingAngle = 0; //Fahrtrichtung wie viel sich der im n�chsten Zyklus �ndert
	private Color playerColor = new Color(255,255,255);
	private Color cannonColor = new Color(153,153,153); //Farbe der Pistole
	private int lives = 20; //Leben des Spielers
	private int livesStart = 20; //startwert Leben
	private boolean paintStatusBar = true; //pr�ft ob Lebensleiste angezeigt werden soll oder nicht
	
	private int shootwait =20; //Clock f�r das Schie�en
	private boolean ableToShoot = false; //pr�ft ob der Spieler schie�en kann oder nicht
	private int weapon =1; //pr�ft welche Waffe ausgew�hlt ist
	public int damage = 2; //Schaden der Waffe
	public int missiledirection=5; //Geschwindigkeit der Geschosse
	private double tolerance=0; //Abweichung der Geschosse
	public boolean stopshoot=true;

	//Konstruktor
	public Player(Coordinate position, double width, double height, double movingAngle, double movingDistance) {
		super(position, width, height);

		setMovingAngle(movingAngle);
		setMovingDistance(movingDistance);
	}

	//getter / setter

	public Shape getTransformedPlayer() {
		return transformedPlayer;
	}

	public void setTransformedPlayer(Shape transformedBody) {
		this.transformedPlayer = transformedBody;
	}

	public double getTurningVelocity() {
		return turningVelocity;
	}

	public void setTurningVelocity(double turningSpeed) {
		this.turningVelocity = turningSpeed;
	}

	public double getDrivingVelocity() {
		return drivingVelocity;
	}

	public void setDrivingVelocity(double drivingSpeed) {
		this.drivingVelocity = drivingSpeed;
	}

	public boolean isAbleToShoot() {
		return ableToShoot;
	}

	public void setAbleToShoot(boolean ableToShoot) {
		this.ableToShoot = ableToShoot;
	}

	public Color getCannonColor() {
		return cannonColor;
	}

	public void setCannonColor(Color cannonColor) {
		this.cannonColor = cannonColor;
	}
	
	public Color getPlayerColor() {
		return playerColor;
	}

	public void setPlayerColor(Color playerColor) {
		this.playerColor = playerColor;
	}
	
	public int getWeapon() {
		return weapon;
	}
	
	public void setWeapon(int weapon) {
		this.weapon = weapon;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getMissiledirection() {
		return missiledirection;
	}

	public void setMissiledirection(int missiledirection) {
		this.missiledirection = missiledirection;
	}

	public double getTolerance() {
		return tolerance;
	}

	public void setTolerance(double d) {
		this.tolerance = d;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

	public int getLivesStart() {
		return livesStart;
	}

	public void setLivesStart(int livesStart) {
		this.livesStart = livesStart;
	}

	public boolean isPaintStatusBar() {
		return paintStatusBar;
	}

	public void setPaintStatusBar(boolean paintStatusBar) {
		this.paintStatusBar = paintStatusBar;
	}

	//Methoden
	public void turnPlayerRight() {
		deltaMovingAngle = turningVelocity; //setzt den �nderungwinkel auf Drehgeschwindigkeit
	}

	public void turnPlayerLeft() {
		deltaMovingAngle = -turningVelocity; //setzt den �nderungwinkel auf Drehgeschwindigkeit
	}

	public void stopTurningPlayer() { //setzt den �nderungswinkel auf 0 --> dreht sich nicht
		deltaMovingAngle = 0;
	}

	public void acceleratePlayer() {
		setMovingDistance(drivingVelocity); //geradeaus fahren
	}

	public void deceleratePlayer() {
		setMovingDistance(-drivingVelocity);//r�ckw�rts fahren
	} 

	public void stopPlayer() { //stehen bleiben
		setMovingDistance(0);
	}
	
	public void shootPlayer() {
		stopshoot=false;
	}
	
	public void stopshootPlayer() {
		stopshoot=true;
	}

	 
	@Override
	public void makeMove() {
		//Winkel des Spielers berechnen
		double newMovingAngle = getMovingAngle() + deltaMovingAngle;
		setMovingAngle(newMovingAngle);//setzen den Bewegungswinkel auf den gerade berechneten Winkel
		super.makeMove(); //Bewegung des Spielers
		
		//Methodenaufruf
		shoottimer();
		weaponChange();
		wallcollision();
		stopshoot();
		

	}

	//Geschosse werden aus dem Ende der Pistolen geschossen
	public Missile shoot() {

		//Mittelpunkt der Spielers bestimmen
		double PlayerCenterX = getObjectPosition().getX() + getWidth() * 0.5;
		double PlayerCenterY = getObjectPosition().getY() + getHeight() * 0.5;
		//Pistolenl�nge bestimmen
		double cannonLength = getWidth() * 0.8;

		//Kugel Gr��e und Winkel bestimmen und setzen
		double missileSize = getWidth() * 0.4;
		double missileAngle = getMovingAngle();
		Coordinate missileDirection= GameObject.angletoCoordinate(missileAngle);
		

		//Pistolenende bestimmen
		double cannonEndX = missileDirection.getX() * cannonLength;
		double cannonEndY = missileDirection.getY() * cannonLength;

		//Koordianten f�r Geschosse erzeugen, zur H�lfte in der Pistoile drin
		Coordinate missileStartPosition = new Coordinate(PlayerCenterX + cannonEndX - missileSize / 2,
				PlayerCenterY + cannonEndY - missileSize / 6);

		Missile missile = new Missile(missileStartPosition, missileSize, missileAngle+getTolerance(), (getMissiledirection())); 
		//neues Geschoss wird erzeugt
		if (getWeapon()==1) {
			missile.setRange(85);
		}else {
			missile.setRange(35);
		}																			
                
		setAbleToShoot(false);
		return missile;
	}
	
	public void wallcollision() {
		if(getObjectPosition().getY()<=0) {
			getObjectPosition().setY(0);
		}
		if(getObjectPosition().getX()<=0) {
			getObjectPosition().setX(0);
		}
		if(getObjectPosition().getY()>=740) {
			getObjectPosition().setY(740);
		}
		if(getObjectPosition().getX()>=940) {
			getObjectPosition().setX(940);
		}
	}
	
	public void stopshoot() {
		if(stopshoot==false) {
		if (isAbleToShoot()) {
            GamePanel.missiles.add(shoot());
           }
		}
	}

	//Firerate
	public void shoottimer() {
		if (shootwait > 0) {
			shootwait -= 1;
		} else {
			setAbleToShoot(true);
			if(getWeapon()==1) {
				shootwait = 20;
			}else {
				shootwait =5;
			}
			
		}
	}
	
	//Waffenwechsel
	public void weaponChange() {
		double min = -0.3;
		double max = 0.3;
		double range=max -min;
		
		if (getWeapon()==1) {

			setDamage(4);
			setMissiledirection(7);
			setTolerance(0);	
		}else {

			setDamage(2);
			setMissiledirection(10);
			setTolerance((Math.random()*range)+min);
		}
	}

	//Zeichnen
	@Override
	public void paintMe(java.awt.Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (isPaintStatusBar()) {
			paintStatusBars(g2d);//zeichnen der Lebensleiste
		}
		paintPlayer(g2d); //zeichnen des Spielers
	}

	private void paintPlayer(Graphics2D g2d) {

		//Spieler
		RoundRectangle2D player = new RoundRectangle2D.Double(getObjectPosition().getX(), getObjectPosition().getY(),
				getWidth(), getHeight(), 360, 360);

		//Waffe
		RoundRectangle2D cannon = new RoundRectangle2D.Double(getObjectPosition().getX() + getWidth() * 1.1,
				getObjectPosition().getY() + getHeight() * 0.5 - getHeight() * 0.14, getWidth() * 0.6,
				getHeight() * 0.28, 0, 0);
		
		RoundRectangle2D playerhelm = new RoundRectangle2D.Double(getObjectPosition().getX()+getWidth()/4-10, getObjectPosition().getY()+getHeight()/4,
				getWidth()/2, getHeight()/2, 360, 360);

		Line2D armRechts = new Line2D.Double(getObjectPosition().getX() + getWidth() * 0.7,
				getObjectPosition().getY() + getHeight() * 0.95, getObjectPosition().getX() + getWidth() * 1.25,
				getObjectPosition().getY() + getHeight() * 0.5 + getHeight() * 0.14);

		Line2D armLinks = new Line2D.Double(getObjectPosition().getX() + getWidth() * 0.7,
				getObjectPosition().getY() + getHeight() * 0.05, getObjectPosition().getX() + getWidth() * 1.25,
				getObjectPosition().getY() + getHeight() * 0.5 - getHeight() * 0.14);


		AffineTransform transform = new AffineTransform();
		transform.rotate(getMovingAngle(),player.getCenterX(),player.getCenterY());
																	
		//Zeichnen vom Spieler gedreht
		g2d.setColor(playerColor);
		Shape transformed = transform.createTransformedShape(player);
		g2d.fill(transformed);
		g2d.setColor(Color.BLACK);
		g2d.setStroke(new BasicStroke((float) 4));
		g2d.draw(transformed);
		setTransformedPlayer(transformed);
		
		//Zeichnen von Armen
		transformed = transform.createTransformedShape(armLinks);
		g2d.draw(transformed);
		transformed = transform.createTransformedShape(armRechts);
		g2d.draw(transformed);

		//Helm
		if(weapon==2) {
			g2d.setColor(cannonColor);
		    transformed = transform.createTransformedShape(playerhelm);
		    g2d.fill(transformed);
		}

		//Pistole zeichnen
		g2d.setColor(cannonColor);
		transformed = transform.createTransformedShape(cannon);
		g2d.fill(transformed);
	}

	private void paintStatusBars(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke((float) 2.0));
		double barOffsetY = getHeight() * 0.4;

		//paint frame
		g2d.setColor(Color.GRAY);
		RoundRectangle2D livesBarFrame = new RoundRectangle2D.Double(Math.round(getObjectPosition().getX()) - 1,
				Math.round(getObjectPosition().getY() - barOffsetY) - 1, getWidth() + 1, 6, 0, 0);
		
		g2d.draw(livesBarFrame);
		if (getLives() > 6) {
			g2d.setColor(Color.GREEN);
		} else {
			g2d.setColor(Color.RED);
		}

		//paint bar
		RoundRectangle2D liveBar = new RoundRectangle2D.Double(Math.round(getObjectPosition().getX()),
				Math.round(getObjectPosition().getY() - barOffsetY), getWidth() / getLivesStart() * (lives), 5, 0, 0);
		g2d.fill(liveBar);
	}
}
