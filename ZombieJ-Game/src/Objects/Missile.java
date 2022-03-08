package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;
import config.Coordinate;

public class Missile extends GameObject { //erweitert von GameObject
	
	private double range = 80;

	public Missile(Coordinate position, double size, double movingAngle, double movingDistance) {//Konstruktor
		super(position, size, size / 3);//Überladen des Konstruktors von GameObject

		setMovingAngle(movingAngle);
		setMovingDistance(movingDistance);
	}

	//getter / setter
	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}
	
	@Override
	public void makeMove() { //Überschreiben der makeMove-Methode
		if (range > 0)
			super.makeMove(); //Aufruf der makeMove-Methode der Oberklasse, solange die Range größer als 0 ist
							  
		range-=1.2;
	}

	@Override
	public void paintMe(java.awt.Graphics g) { //Zeichnen der Kugeln
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);

		AffineTransform transform = new AffineTransform(); //Rotation, dabei bleibt die Parallelität der Linien erhalten
		
		//erzeugen eines Rechtecks
		RoundRectangle2D missileShape = new RoundRectangle2D.Double(getObjectPosition().getX(),
				getObjectPosition().getY(), getWidth(), getHeight(), 0, 0);

		transform.rotate(getMovingAngle(), missileShape.getCenterX(), missileShape.getCenterY());
		//Rotieren um den Bewegungswinkel dabei dient der Mittelpunkt als Ankerpunkt zum drehen	
		
		Shape transformedMissileShape = transform.createTransformedShape(missileShape);

		g2d.fill(transformedMissileShape); //Zeichen des Quadrats
	}
}


