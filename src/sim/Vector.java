package sim;

public class Vector {
	private double x;
	private double y;
	
	private double tailX;
	private double tailY;
	
	private double angle;
	private double magnitude;
	
	public Vector(double x, double y) {
		this(x, y, 0, 0);
	}
	
	public Vector(double x, double y, double tailX, double tailY) {
		this.x = x;
		this.y = y;
		this.tailX = tailX;
		this.tailY = tailY;
		
		angle = Math.atan2(y - tailY, x - tailX);
		magnitude = Math.sqrt(
			Math.pow(y - tailY, 2) + 
			Math.pow(x - tailX, 2)
		);
	}
	
	public Vector(Coord head, Coord tail) {
		this(head.getX(), head.getY(), tail.getX(), tail.getY());
	}
	
	public double getX() { return x; }
	public double getY() { return y; }
	public double getTailX() { return tailX; }
	public double getTailY() { return tailY; }
	public double getAngle() { return angle; }
	public double getMag() { return magnitude; }
	
	public static Vector fromPolar(double r, double theta, double tailX, double tailY) {
		return new Vector(0, 0);
	}
}
