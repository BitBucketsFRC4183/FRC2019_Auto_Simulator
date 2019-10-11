package autocode;
public class DriveConstants {
    public static final double MAX_ALLOWED_SPEED_IPS = 8.0*12.0;
    public static final double MAX_ALLOWED_TURN_DPS  = 180.0;
    public static final double MAX_ALLOWED_TURN_RADPS = Math.toRadians(MAX_ALLOWED_TURN_DPS);
    public static final double STANDARD_G_FTPSPS = 32.1740;
    public static final double MAX_LAT_ACCELERATION_IPSPS = STANDARD_G_FTPSPS * 12.0;
    public static final double LOCK_DEADBAND_IPS = 12.0;  // ignore button command changes above this speed
    public static final double ALIGN_DEADBAND_DPS = 45.0; // ignore button command changes above this turn rate

    public static final double WHEEL_TRACK_INCHES = 23.5;
    public static final double WHEEL_DIAMETER_INCHES = 6.0;
    public static final double WHEEL_CIRCUMFERENCE_INCHES = Math.PI*WHEEL_DIAMETER_INCHES;
    public static final double TRACK_TO_CIRCUMFERENCE_RATIO = WHEEL_TRACK_INCHES / WHEEL_DIAMETER_INCHES;
    public static final double WHEEL_ROTATION_PER_FRAME_DEGREES = TRACK_TO_CIRCUMFERENCE_RATIO / 360.0;
    
    public static final int DRIVE_MOTOR_MOTION_ACCELERATION_NATIVE_TICKS = (int) (4346/1.5); // 0.26 g on wood//DRIVE_MOTOR_MOTION_CRUISE_SPEED_NATIVE_TICKS;
    public static final double DRIVE_MOTOR_MOTION_ACCELERATION_IPSPS = ticksP100ToIps(DRIVE_MOTOR_MOTION_ACCELERATION_NATIVE_TICKS) * 10;
    
    public static double ticksP100ToIps(int ticksP100)
    {
        double rps = ticksP100 * 10.0 / DRIVE_MOTOR_NATIVE_TICKS_PER_REV;
        return rps * WHEEL_CIRCUMFERENCE_INCHES;
    }
    
    public static final double DRIVE_MOTOR_NATIVE_TICKS_PER_REV=8192; // AMT-201 at 2048 pulses per rev
}
