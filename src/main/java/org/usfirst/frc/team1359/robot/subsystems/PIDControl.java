/**
 * 
 */
package org.usfirst.frc.team1359.robot.subsystems;

/**
 * @author 1359
 *
 */
public class PIDControl {
	double SetPoint;
	double errSum, LastErr;
	double ki, kp, kd;

	public PIDControl(double Kp, double Ki, double Kd) {

		kp = Kp;
		ki = Ki;
		kd = Kd;
	}

	public void SetPoint(double Target) {

		SetPoint = Target;

		LastErr = 0;
		errSum = 0;
	}

	public double Compute(double Input) {

		double error = SetPoint - Input;
		errSum += (error);
		double dErr = (error - LastErr);

		LastErr = error;
		return kp * error + ki * errSum + kd * dErr;

	}

	public void SetTunings(double Kp, double Ki, double Kd) {

		kp = Kp;
		ki = Ki;
		kd = Kd;
	}

}
