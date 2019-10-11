package autocode;
public class MotionPoint {
    public double l_vel;
    public double l_pos;
    public double l_acc;

    public double r_pos;
    public double r_vel;
    public double r_acc;

    public double left_vel;
    public double right_vel;

    public double t;
    
    public double x;
    public double y;

    public MotionPoint(double l_pos, double l_vel, double l_acc, double r_pos, double r_vel, double left_vel, double right_vel)
    {
        //long story short: r_acc not in constructor bc it is calculated after entirety of other points
        //are calculated. But it shouldn't be null
        this.l_vel=l_vel;
        this.l_pos=l_pos;
        this.l_acc=l_acc;
        this.r_pos=r_pos;
        this.r_vel=r_vel;

        this.left_vel  = left_vel;
        this.right_vel = right_vel;
    }

    public void setT(double time) {
        t = time;
    }
    
    public void setX(double x) {
    	this.x = x;
    }
    
    public void setY(double y) {
    	this.y = y;
    }
}
