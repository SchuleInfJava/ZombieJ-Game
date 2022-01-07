package Game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.RoundRectangle2D;

public class Zombie extends Player {

	private Player player;
	private int zombielives = 3;

	public Zombie(Coordinate position, double size, double movingAngle, double movingDistance, Player player) {
		super(position, size, size, movingAngle, movingDistance);
		this.player = player;

		setTurningVelocity(TURNING_VELOCITY * 10);
		setDrivingVelocity(DRIVING_VELOCIY / 2);

		setLives(zombielives);
		setLivesStart(zombielives);

		setPaintStatusBar(false);

		acceleratePlayer();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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

	@Override
	public void paintMe(java.awt.Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (isPaintStatusBar()) {
			paintStatusBars(g2d);// zeichnet die lebensleiste
		}
		paintZombie(g2d); // zeichnet den panzer

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
		g2d.setColor(new Color(0, 150, 0));
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
