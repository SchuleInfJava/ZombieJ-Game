package Objects;

import config.Coordinate;

public abstract class GameObject { // abstracte klasse wie ein interface

	// Variablen
	private Coordinate objectPosition;
	private double width;
	private double height;
	private double movingAngle;// bewegungsrichtung
	private double movingDistance;// bewegungsdistance

	// konstructor der drei variablen übergibt
	public GameObject(Coordinate objectPosition, double width, double height) {
		this.objectPosition = objectPosition;
		this.width = width;
		this.height = height;
		movingAngle = 0;
		movingDistance = 0;
	}

	/*
	 * Getter / Setter
	 */
	public Coordinate getObjectPosition() {
		return objectPosition;
	}

	public void setObjectPosition(Coordinate objectPosition) {
		this.objectPosition = objectPosition;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getMovingAngle() {
		return movingAngle;
	}

	public void setMovingAngle(double movingAngle) {
		this.movingAngle = movingAngle;
	}

	public double getMovingDistance() {
		return movingDistance;
	}

	public void setMovingDistance(double movingDistance) {
		this.movingDistance = movingDistance;
	}

	// ob sie nebeneinander liegen
	public boolean isLeftOf(GameObject that) {
		return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
	}

	// ob zwei Objecte übereinander liegen
	public boolean isAbove(GameObject that) {
		return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
	}

	// kollisionsabfrage für Vierecke
	public boolean touches(GameObject that) {
		if (this.isLeftOf(that))
			return false;
		if (that.isLeftOf(this))
			return false;
		if (this.isAbove(that))
			return false;
		if (that.isAbove(this))
			return false;

		return true;
	}

	// Bewegungsrichtungen in einen bewegungsvector umgewandelt winkel zu koordinate
	public static Coordinate polarToCartesianCoordinates(double angle) {

		// X-Achse zeigt nach Osten, Y-Achse zeigt nach Süden beim Zeichnen
		double x = Math.cos(angle);
		double y = Math.sin(angle);

		return new Coordinate(x, y);
	}

	// bewegung
	public void moveGameObject() {

		Coordinate direction = polarToCartesianCoordinates(movingAngle);// setzt vector auf richtung

		objectPosition.setX(objectPosition.getX() + direction.getX() * movingDistance);// setzt das neue x auf altes x +
																						// vektor +entfernung
		objectPosition.setY(objectPosition.getY() + direction.getY() * movingDistance);
	}

	// wird bei jedem tick aufgerufen bewegung der figuren
	public void makeMove() {
		moveGameObject();// Methode aufruf
	}

	// zeichnet jedes object wird in den unterklassen ausprogrammiert
	protected abstract void paintMe(java.awt.Graphics g);

}
