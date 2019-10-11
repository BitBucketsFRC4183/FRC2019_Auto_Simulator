package sim.components;

import java.awt.Graphics;

import sim.Constants;
import sim.Coord;
import sim.Field;



public class CargoBay {
	private static final double CARGO_YLEN = 4*12 + 7.75;
	private static final double CARGO_XLEN = 7*12 + 11.75;
	// separation from middle line
	private static final double CARGO_SEP = 9.0;
	
	
	
	private static final Coord A = new Coord(-CARGO_SEP + Constants.FIELD_HALF_HEIGHT_IN,              -CARGO_YLEN/2);
	private static final Coord B = new Coord(-CARGO_SEP + Constants.FIELD_HALF_HEIGHT_IN - CARGO_XLEN, -CARGO_YLEN/2);
	private static final Coord C = new Coord(-CARGO_SEP + Constants.FIELD_HALF_HEIGHT_IN - CARGO_XLEN,  CARGO_YLEN/2);
	private static final Coord D = new Coord(-CARGO_SEP + Constants.FIELD_HALF_HEIGHT_IN,               CARGO_YLEN/2);
	
	
	
	public static void paint(Graphics g) {
		Field.drawLine(A, B, g);
		Field.drawLine(B, C, g);
		Field.drawLine(C, D, g);
		Field.drawLine(D, A, g);
		
		
	}
}
