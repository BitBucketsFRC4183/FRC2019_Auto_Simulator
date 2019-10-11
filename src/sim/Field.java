package sim;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import autocode.AutonomousConstants;
import autocode.DriveConstants;
import autocode.MotionPoint;
import autocode.PathFinder;
import autocode.Spline;
import autocode.TrajectoryFinder;
import autocode.TrajectoryFinder.MotionProfile;
import autocode.Waypoint;
import sim.components.CargoBay;
import sim.components.HabPlatform;

public class Field extends JPanel {
	// we sister stan Java!!!
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	
	private static final int WIDTH = Constants.WIDTH;
	private static final int HEIGHT = Constants.HEIGHT;
	
	
	
	private static final double DIST_FROM_MIDDLE = 9.0/12;
	
	private static final Coord TEAM_LINE_A = new Coord(Constants.FIELD_HALF_HEIGHT_IN - DIST_FROM_MIDDLE, -Constants.FIELD_WIDTH_IN / 2);
	private static final Coord TEAM_LINE_B = new Coord(Constants.FIELD_HALF_HEIGHT_IN - DIST_FROM_MIDDLE, Constants.FIELD_WIDTH_IN / 2);
	
	private static final Coord CENTER_LINE_A = new Coord(Constants.FIELD_HALF_HEIGHT_IN, -Constants.FIELD_WIDTH_IN / 2);
	private static final Coord CENTER_LINE_B = new Coord(Constants.FIELD_HALF_HEIGHT_IN, Constants.FIELD_WIDTH_IN / 2);
	
	
	
	
	private TrajectoryFinder tj;
	private MotionPoint mp;
	
	private boolean pause = true;
	
	private Coord robotPos;
	private double robotAngle;
	
	
	
	
	public Field() {
		frame = new JFrame("Bit Buckets Auto Simulator");
		
		frame.add(this);
		
		
		frame.setSize(WIDTH, HEIGHT);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		this.setSize(WIDTH, HEIGHT);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		
		
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		frame.addKeyListener(new KL());
	}
	
	
	
	public boolean getPaused() {
		return pause;
	}
	
	
	
	
	@Override
	public void paintComponent(Graphics g) {
		//frame.setBounds(0, 0, WIDTH, HEIGHT);
		
		//setBounds(0, 0, WIDTH, HEIGHT);
		
		
		
		super.paintComponent(g);
		
		// paint background
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		
		
		g.setColor(Color.BLUE);
		
		HabPlatform.paint(g);
		CargoBay.paint(g);
		drawLine(TEAM_LINE_A, TEAM_LINE_B, g);
		
		drawLineColor(CENTER_LINE_A, CENTER_LINE_B, g, Color.BLACK);
		
		
		
		drawTrajectory(g);
		
		
		
		// draw current ideal and estimated real MP
		if (mp != null) {
			Coord c = new Coord(mp.x, mp.y);
			
			double angle = mp.r_pos;
			
			Coord c2 = new Coord(
				mp.x + mp.l_vel * Math.cos(angle),
				mp.y + mp.l_vel * Math.sin(angle)
			);
			
			Point p = new Point(c);
			Point p2 = new Point(c2);
			
			g.setColor(Color.BLUE);
			g.drawLine(p.getX(), p.getY(), p2.getX(), p2.getY());
			
			g.setColor(Color.GREEN);
			g.drawRect(p.getX() - 2, p.getY() - 2, 4, 4);
			
			// v = rw
			// r = v/w = linvel/rvel
			
			double r = mp.l_vel / mp.r_vel;
			//System.out.println("vel: " + mp.l_vel + " | rot vel: " + mp.r_vel);

			double cx = mp.x + r * Math.cos(angle + Math.PI / 2);
			double cy = mp.y + r * Math.sin(angle + Math.PI / 2);
			
			Coord center = new Coord(cx, cy);
			Point pc = new Point(center);
			
			int R = (int) Math.abs(r * Constants.WIDTH / Constants.FIELD_WIDTH_IN);
			
			g.drawArc(pc.getX() - R, pc.getY() - R, 2 * R, 2 * R, 0, 360);
			
			
			
			
			// robotPos = estimated actual robot position
			c2 = new Coord(
				robotPos.getX() + mp.l_vel * Math.cos(angle),
				robotPos.getY() + mp.l_vel * Math.sin(angle)
			);
			//System.out.println(robotPos.getX() + ", " + robotPos.getY());
			
			p = new Point(robotPos);
			p2 = new Point(c2);
			
			g.setColor(Color.RED);
			//g.drawLine(p.getX(), p.getY(), p2.getX(), p2.getY());
			
			g.setColor(Color.RED);
			g.drawRect(p.getX() - 2, p.getY() - 2, 4, 4);
			
			
			
			Coord cA, cB, cC, cD;
			cA = new Coord(
				robotPos.getX() + Constants.ROBOT_DIAG_IN/2 * Math.cos(Constants.ROBOT_DIAG_ANGLE + angle),
				robotPos.getY() + Constants.ROBOT_DIAG_IN/2 * Math.sin(Constants.ROBOT_DIAG_ANGLE + angle)
			);
			cB = new Coord(
				robotPos.getX() + Constants.ROBOT_DIAG_IN/2 * Math.cos(-Constants.ROBOT_DIAG_ANGLE + angle),
				robotPos.getY() + Constants.ROBOT_DIAG_IN/2 * Math.sin(-Constants.ROBOT_DIAG_ANGLE + angle)
			);
			cC = new Coord(
				robotPos.getX() + Constants.ROBOT_DIAG_IN/2 * Math.cos(Math.PI + Constants.ROBOT_DIAG_ANGLE + angle),
				robotPos.getY() + Constants.ROBOT_DIAG_IN/2 * Math.sin(Math.PI + Constants.ROBOT_DIAG_ANGLE + angle)
			);
			cD = new Coord(
				robotPos.getX() + Constants.ROBOT_DIAG_IN/2 * Math.cos(Math.PI - Constants.ROBOT_DIAG_ANGLE + angle),
				robotPos.getY() + Constants.ROBOT_DIAG_IN/2 * Math.sin(Math.PI - Constants.ROBOT_DIAG_ANGLE + angle)
			);
			
			drawLineColor(cA, cB, g, Color.BLACK);
			drawLineColor(cB, cC, g, Color.BLACK);
			drawLineColor(cC, cD, g, Color.BLACK);
			drawLineColor(cD, cA, g, Color.BLACK);
		}
	}
	
	
	
	
	public static void drawLineColor(Coord c1, Coord c2, Graphics g, Color c) {
		Point p1 = new Point(c1);
		Point p2 = new Point(c2);
		
		g.setColor(c);
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
	}
	
	
	
	public static void drawLine(Coord c1, Coord c2, Graphics g) {
		Point p1 = new Point(c1);
		Point p2 = new Point(c2);
		
		
		
		g.setColor(Color.BLUE);
		g.drawLine(p1.getX(), p1.getY(), p2.getX(), p2.getY());
		if (Constants.USE_FULL_FIELD) {
			g.setColor(Color.RED);
			g.drawLine(p1.getX(), Constants.HEIGHT - p1.getY(), p2.getX(), Constants.HEIGHT - p2.getY());
		}
	}
	
	
	
	
	
	public void setTrajectory(TrajectoryFinder tj) {
		this.tj = tj;
		
		// starting point
		Waypoint wp = tj.getPathFinder().getWaypoints()[0];
		
		double x = wp.x;
		double y = wp.y;
		double angle = wp.deg * Math.PI / 180;
		
		// set initial values of robot
		robotPos = new Coord(x, y);
		robotAngle = angle;
	}
	
	public void updateAuto(MotionPoint mp, MotionPoint lastMP) {
		this.mp = mp;
		
		// assume velocity changed linearly during time interval
		// v = v0 + at
		// theta = theta0 + omega * t + 1/2 alpha t^2
		// x' = v * cos(theta) = (v0 + at) * cos(theta0 + omega * t + 1/2 alpha t^2)
		// y' = v * sin(theta) = (v0 + at) * sin(theta0 + omega * t + 1/2 alpha t^2)
		// not nicely integratable...
		
		double A = lastMP.l_vel;
		double B = 0;//lastMP.l_acc;
		double C = lastMP.r_pos;
		double D = lastMP.r_vel;
		double E = 0;//lastMP.r_acc;
		
		double T = AutonomousConstants.LOOP_MS_PER / 1000.0;
		
		double x = robotPos.getX();
		double y = robotPos.getY();
		
		// Riemann sum with 100 intervals, a bit overkill mayhaps
		for (int i = 0; i < 100; i++) {
			double t = i * T / 100.0;
			
			double v = A + B * t;
			double theta = C + D * t + 1/2 * E * t * t;
			
			x += T/100 * v * Math.cos(theta);
			y += T/100 * v * Math.sin(theta);
		}
		
		//System.out.println(x + ", " + y);
		
		robotPos = new Coord(x, y);
		robotAngle = C + D * T;
		
		repaint();
	}
	
	
	
	public void drawTrajectory(Graphics g) {
		//if (true) { return; }
		if (tj == null) { return; }
		
		//System.out.println("drawing trajectory...");
		
		PathFinder pf = tj.getPathFinder();
		Spline[] splines = pf.getSplines();
		
		//System.out.println(splines.length + " splines");
		
		Coord lastC = new Coord(0, 0);
		boolean first = true;
		
		for (int i = 0; i < splines.length; i++) {
			//System.out.println("Spline " + i);
			Spline sp = splines[i];
			
			double[] xcoeffs = sp.xcoef;
			double[] ycoeffs = sp.ycoef;
			
			for (double s = 0; s <= 1; s += 0.1) {
				//System.out.println("Parameter s: " + s);
				double x = sp.evaluateFunction(xcoeffs, s);
				double y = sp.evaluateFunction(ycoeffs, s);
				
				Coord c = new Coord(x, y);
				
				if (first == false) {
					drawLineColor(c, lastC, g, Color.BLACK);
				}
				
				lastC = c;
				
				first = false;
			}
		}
	}
	
	
	
	private class KL implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			int code = e.getKeyCode();
			
			if (code == KeyEvent.VK_SPACE) {
				pause = !pause;
			}
		}
		
		@Override
		public void keyReleased(KeyEvent e) {}

		@Override
		public void keyTyped(KeyEvent e) {}
	}
	
	
	
	
	
	public static void main(String[] args) {
		Field f = new Field();
		
		double maxLength = Math.sqrt(Math.pow(Constants.ROBOT_LENGTH_IN, 2) + Math.pow(Constants.ROBOT_WIDTH_IN, 2));
		Waypoint[] waypoints = {
			new Waypoint(
				4 * 12 + (3*12)/2, 0, 0
			),
			new Waypoint(
				4 * 12 + (3*12 + 11.25) + maxLength / 2, 0, 0
			),
			new Waypoint(
				Constants.FIELD_HALF_HEIGHT_IN - 9 - (7*12 + 11.75) - Constants.ROBOT_LENGTH_IN / 2 - 2.0, 0.25*(4*12 + 7.75), 0
			)
		};
		
		TrajectoryFinder tj = new TrajectoryFinder(
			MotionProfile.TRAPEZOIDAL,
			PathFinder.PathType.CUBIC_HERMITE,
			waypoints,
			666, // acc
			8*12, // speed
			-666,
			0,
			0
		);
		
		f.setTrajectory(tj);
		System.out.println("set trajectory");
		System.out.println(DriveConstants.DRIVE_MOTOR_MOTION_ACCELERATION_IPSPS);
		
		MotionPoint[] mps = tj.getMotionPoints();
		
		System.out.println("Execution time: " + tj.t_total + "s");
		System.out.println("Loop execution time: " + mps.length * AutonomousConstants.LOOP_MS_PER / 1000 + "s");
		
		System.out.println(Constants.ROBOT_DIAG_ANGLE * 180 / Math.PI);
		
		long millis1 = 0;
		long millis2 = 0;
		
		for (int i = 0; i < mps.length; i++) {
			if (f.getPaused()) {
				i = i - 1;
			} else {
				if (i == 0) {
					millis1 = System.currentTimeMillis();
					
					f.updateAuto(mps[0], mps[0]);
				} else {
					// from old to new MP -> used to estimate real position
					f.updateAuto(mps[i], mps[i - 1]);
				}
				
				if (i == mps.length - 1) {
					millis2 = System.currentTimeMillis();
					
					System.out.println("Real loop execution time: " + (millis2 - millis1) / 1000.0 + "s");
				}
			}
			
			try {
				Thread.sleep((long) AutonomousConstants.LOOP_MS_PER);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}