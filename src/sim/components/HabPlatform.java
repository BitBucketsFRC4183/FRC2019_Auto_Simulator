package sim.components;

import java.awt.Graphics;

import sim.Coord;
import sim.Field;

public class HabPlatform {
	private static final double LEVEL0_YLEN = 12*12 + 6.5;
	private static final double LEVEL0_XLEN = 3*12 + 11.25;
	
	private static final double LEVEL1_XLEN = 3*12;
	private static final double LEVEL1_YLEN = 10*12 + 8.0;
	
	private static final double LEVEL2_XLEN = 4*12;
	private static final double LEVEL2_YLEN = 3*12 + 4.0;
	
	private static final double LEVEL3_XLEN = 4*12;
	private static final double LEVEL3_YLEN = 4*12;
	
	private static final double DEPOT_XLEN = 3*12 + 9.0;
	private static final double DEPOT_YLEN = 1*12 + (10.0+5.0/8);
	
	
	
	
	
	private static final Coord LEVEL0_A = new Coord(LEVEL3_XLEN,                LEVEL0_YLEN / 2);
	private static final Coord LEVEL0_B = new Coord(LEVEL3_XLEN + LEVEL0_XLEN,  LEVEL0_YLEN / 2);
	private static final Coord LEVEL0_C = new Coord(LEVEL3_XLEN + LEVEL0_XLEN, -LEVEL0_YLEN / 2);
	private static final Coord LEVEL0_D = new Coord(LEVEL3_XLEN,               -LEVEL0_YLEN / 2);
	
	
	
	
	private static final Coord LEVEL1_A = new Coord(LEVEL3_XLEN,                LEVEL1_YLEN / 2);
	private static final Coord LEVEL1_B = new Coord(LEVEL3_XLEN + LEVEL1_XLEN,  LEVEL1_YLEN / 2);
	private static final Coord LEVEL1_C = new Coord(LEVEL3_XLEN + LEVEL1_XLEN, -LEVEL1_YLEN / 2);
	private static final Coord LEVEL1_D = new Coord(LEVEL3_XLEN,               -LEVEL1_YLEN / 2);
	
	
	
	private static final Coord LEVEL2_RIGHT_A = new Coord(0,           LEVEL3_YLEN/2);
	private static final Coord LEVEL2_RIGHT_B = new Coord(LEVEL3_XLEN, LEVEL3_YLEN/2);
	private static final Coord LEVEL2_RIGHT_C = new Coord(LEVEL3_XLEN, LEVEL3_YLEN/2 + LEVEL2_YLEN);
	private static final Coord LEVEL2_RIGHT_D = new Coord(0,           LEVEL3_YLEN/2 + LEVEL2_YLEN);
	
	private static final Coord LEVEL2_LEFT_A = new Coord(0,           -LEVEL3_YLEN/2 - LEVEL2_YLEN);
	private static final Coord LEVEL2_LEFT_B = new Coord(LEVEL3_XLEN, -LEVEL3_YLEN/2 - LEVEL2_YLEN);
	private static final Coord LEVEL2_LEFT_C = new Coord(LEVEL3_XLEN, -LEVEL3_YLEN/2);
	private static final Coord LEVEL2_LEFT_D = new Coord(0,           -LEVEL3_YLEN/2);
	
	
	
	private static final Coord LEVEL3_A = new Coord(0, -LEVEL3_YLEN/2);
	private static final Coord LEVEL3_B = new Coord(0,  LEVEL3_YLEN/2);
	
	
	
	private static final Coord DEPOT_RIGHT_A = new Coord(0,          LEVEL3_YLEN/2 + LEVEL2_YLEN + DEPOT_YLEN);
	private static final Coord DEPOT_RIGHT_B = new Coord(DEPOT_XLEN, LEVEL3_YLEN/2 + LEVEL2_YLEN + DEPOT_YLEN);
	private static final Coord DEPOT_RIGHT_C = new Coord(DEPOT_XLEN, LEVEL3_YLEN/2 + LEVEL2_YLEN);
	private static final Coord DEPOT_RIGHT_D = new Coord(0,          LEVEL3_YLEN/2 + LEVEL2_YLEN);
	
	private static final Coord DEPOT_LEFT_A = new Coord(0,          -LEVEL3_YLEN/2 - LEVEL2_YLEN);
	private static final Coord DEPOT_LEFT_B = new Coord(DEPOT_XLEN, -LEVEL3_YLEN/2 - LEVEL2_YLEN);
	private static final Coord DEPOT_LEFT_C = new Coord(DEPOT_XLEN, -LEVEL3_YLEN/2 - LEVEL2_YLEN - DEPOT_YLEN);
	private static final Coord DEPOT_LEFT_D = new Coord(0,          -LEVEL3_YLEN/2 - LEVEL2_YLEN - DEPOT_YLEN);
	
	
	
	
	
	public static void paint(Graphics g) {
		// level 0 hab (area below level 1, with 15 degree incline leading up to it)
		Field.drawLine(LEVEL0_A, LEVEL0_B, g);
		Field.drawLine(LEVEL0_B, LEVEL0_C, g);
		Field.drawLine(LEVEL0_C, LEVEL0_D, g);
		Field.drawLine(LEVEL0_D, LEVEL0_A, g);
		
		// level 1 hab
		Field.drawLine(LEVEL1_A, LEVEL1_B, g);
		Field.drawLine(LEVEL1_B, LEVEL1_C, g);
		Field.drawLine(LEVEL1_C, LEVEL1_D, g);
		Field.drawLine(LEVEL1_D, LEVEL1_A, g);
		
		// angle between level 0 and level 1
		Field.drawLine(LEVEL0_B, LEVEL1_B, g);
		Field.drawLine(LEVEL0_C, LEVEL1_C, g);
		
		// right level 2 hab
		Field.drawLine(LEVEL2_RIGHT_A, LEVEL2_RIGHT_B, g);
		Field.drawLine(LEVEL2_RIGHT_B, LEVEL2_RIGHT_C, g);
		Field.drawLine(LEVEL2_RIGHT_C, LEVEL2_RIGHT_D, g);
		Field.drawLine(LEVEL2_RIGHT_D, LEVEL2_RIGHT_A, g);
		
		// left level 2 hab
		Field.drawLine(LEVEL2_LEFT_A, LEVEL2_LEFT_B, g);
		Field.drawLine(LEVEL2_LEFT_B, LEVEL2_LEFT_C, g);
		Field.drawLine(LEVEL2_LEFT_C, LEVEL2_LEFT_D, g);
		Field.drawLine(LEVEL2_LEFT_D, LEVEL2_LEFT_A, g);
		
		// level 3 hab (all other sides drawn already)
		Field.drawLine(LEVEL3_A, LEVEL3_B, g);
		
		// right depot
		Field.drawLine(DEPOT_RIGHT_A, DEPOT_RIGHT_B, g);
		Field.drawLine(DEPOT_RIGHT_B, DEPOT_RIGHT_C, g);
		Field.drawLine(DEPOT_RIGHT_C, DEPOT_RIGHT_D, g);
		Field.drawLine(DEPOT_RIGHT_D, DEPOT_RIGHT_A, g);
		
		// left depot
		Field.drawLine(DEPOT_LEFT_A, DEPOT_LEFT_B, g);
		Field.drawLine(DEPOT_LEFT_B, DEPOT_LEFT_C, g);
		Field.drawLine(DEPOT_LEFT_C, DEPOT_LEFT_D, g);
		Field.drawLine(DEPOT_LEFT_D, DEPOT_LEFT_A, g);
	}
}
