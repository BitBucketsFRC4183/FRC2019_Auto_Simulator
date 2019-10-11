package autocode;
import autocode.TrajectoryFinder.MotionProfile;

public class Test {
	public static void main(String[] args) {
		Waypoint[] wps = {
			new Waypoint(0, 0, 0),
			new Waypoint(0.1, 2, 10),
			new Waypoint(3, 4, 45),
			new Waypoint(7, 7, 35),
			new Waypoint(10, 10, 0)
		};
		
		TrajectoryFinder tj = new TrajectoryFinder(
			TrajectoryFinder.MotionProfile.TRAPEZOIDAL, PathFinder.PathType.CUBIC_HERMITE, wps, 1, 5, -1, 0, 0
		);
		
		MotionPoint[] mps = tj.getMotionPoints();
		
		double x = 0;
		double y = 0;
		
		double lastT = 0;
		
		for (int i = 0; i < mps.length; i++) {
			double t = mps[i].left_vel;
			double dt = t - lastT;
			
			lastT = t;
			
			x += mps[i].l_vel * Math.cos(mps[i].r_pos) * dt;
			y += mps[i].l_vel * Math.sin(mps[i].r_pos) * dt;
			
			System.out.println(Math.round(x * 100) / 100.0 + ", " + Math.round(100 * y) / 100.0 + " -> " + Math.round(mps[i].r_pos * 180 / Math.PI));
		}
	}
}
