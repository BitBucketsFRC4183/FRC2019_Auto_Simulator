package sim;

public class Constants {
	public static final boolean USE_FULL_FIELD = false;
	
	public static final double FIELD_WIDTH_IN  = 27.0 * 12;
	public static final double FIELD_HALF_HEIGHT_IN = 27.0 * 12;
	public static final double FIELD_HEIGHT_IN = 2 * FIELD_HALF_HEIGHT_IN;
	
	public static final int HEIGHT = 800;
	public static final int WIDTH = (int) (HEIGHT * FIELD_WIDTH_IN /
		((USE_FULL_FIELD) ? FIELD_HEIGHT_IN : FIELD_HALF_HEIGHT_IN)
	);
	
	public static final double ROBOT_WIDTH_IN = 28;
	public static final double ROBOT_LENGTH_IN = 30;
	
	public static final double ROBOT_DIAG_ANGLE = Math.atan2(ROBOT_WIDTH_IN, ROBOT_LENGTH_IN);
	public static final double ROBOT_DIAG_IN = Math.sqrt(ROBOT_WIDTH_IN * ROBOT_WIDTH_IN + ROBOT_LENGTH_IN * ROBOT_LENGTH_IN);
}
