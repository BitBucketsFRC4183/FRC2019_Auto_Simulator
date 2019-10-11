package sim;

public class Point {
	private int x;
	private int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point(Coord c) {
		double y = c.getY() + Constants.FIELD_WIDTH_IN  / 2;
		double x = c.getX();
		
		y *= Constants.WIDTH  / Constants.FIELD_WIDTH_IN;
		if (Constants.USE_FULL_FIELD) {
			x *= Constants.HEIGHT / Constants.FIELD_HEIGHT_IN;
		} else {
			x *= Constants.HEIGHT / Constants.FIELD_HALF_HEIGHT_IN;
		}
		x = Constants.HEIGHT - x;
		
		
		this.y = (int) x;
		this.x = (int) y;
		
		//System.out.println("(" + this.x + ", " + this.y + ")");
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
}
