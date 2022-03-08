package Objects;

import config.Coordinate;

public abstract class GameObject { //abstrakte Klasse wie ein Interface

	//Variablen
	private Coordinate objectPosition;
	private double width;
	private double height;
	private double movingAngle;//Bewegungsrichtung
	private double movingDistance;//Bewegungsdistanz

	//Konstruktor der Variablen
	public GameObject(Coordinate objectPosition, double width, double height) {
		this.objectPosition = objectPosition;
		this.width = width;
		this.height = height;
		movingAngle = 0;
		movingDistance = 0;
	}

	// getter / setter
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

	//prüft ob Objekte nebeneinander liegen
	public boolean isLeftOf(GameObject that) {
		return this.getObjectPosition().getX() + this.getWidth() < that.getObjectPosition().getX();
	}

	//prüft ob Objekte übereinander liegen
	public boolean isAbove(GameObject that) {
		return this.getObjectPosition().getY() + this.getHeight() < that.getObjectPosition().getY();
	}

	//Kollisionsabfrage
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

	//Bewegungsrichtungen in einen Bewegungsvektor umgewandelt; Winkel zu Koordinate
	public static Coordinate angletoCoordinate(double angle) {

		//X-Achse zeigt nach Osten, Y-Achse zeigt nach Süden beim Zeichnen
		double x = Math.cos(angle);
		double y = Math.sin(angle);

		return new Coordinate(x, y);
	}

	//Bewegung
	public void moveGameObject() {

		Coordinate direction = angletoCoordinate(movingAngle);//setzt Vektor auf Richtung

		objectPosition.setX(objectPosition.getX() + direction.getX() * movingDistance);//setzt neue x auf altes x +
																						//Vektor + Entfernung
		objectPosition.setY(objectPosition.getY() + direction.getY() * movingDistance);
	}

	//bei jedem Tick aufgerufen; Bewegung der figuren
	public void makeMove() {
		moveGameObject();// Methode aufruf
	}

	//zeichnet jedes Objekt; wird in den Unterklassen ausprogrammiert
	protected abstract void paintMe(java.awt.Graphics g);
}
