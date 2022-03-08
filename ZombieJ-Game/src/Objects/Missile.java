package Objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.RoundRectangle2D;

import config.Coordinate;

public class Missile extends GameObject { // erweitert von GameObject
	
	private double range = 80;

	public Missile(Coordinate position, double size, double movingAngle, double movingDistance) {// konstruktor
		super(position, size, size / 3);// überlad den konstruktor von GameObject

		setMovingAngle(movingAngle);
		setMovingDistance(movingDistance);
	}

	// Getter / Setter
	public double getRange() {
		return range;
	}

	public void setRange(double range) {
		this.range = range;
	}
	

	@Override
	public void makeMove() { // überschreibend die makeMove methode
		if (range > 0)
			super.makeMove(); // aufruf der makeMove methode der oberklasse solange die range größer als null
							  // ist
		range-=1.2;
	}

	@Override
	public void paintMe(java.awt.Graphics g) { // zeichnen der kugeln
		
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.YELLOW);

		AffineTransform transform = new AffineTransform(); // rotation dabei bleibt die parallelität der linien erhalten
		
		// erzeugen ein Rechteck
		RoundRectangle2D missileShape = new RoundRectangle2D.Double(getObjectPosition().getX(),
				getObjectPosition().getY(), getWidth(), getHeight(), 0, 0);

		transform.rotate(getMovingAngle(), missileShape.getCenterX(), missileShape.getCenterY());
		// rotieren es um den bewegungswinkel und nehmen den mittelpunkt als ankerpunkt zum drehen
		
		Shape transformedMissileShape = transform.createTransformedShape(missileShape);

		g2d.fill(transformedMissileShape); // zeichen des quadrates

	}

}


