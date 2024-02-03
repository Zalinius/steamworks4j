package com.codedisaster.steamworks;

/**
 * Gyro post processing
 */
public class SteamInputMotionDataV2 {

	/**
	 * C++ API uses a packed struct of floats. To avoid costly JNI calls, we just
	 * use a plain array here and "convert" via getter member functions.
	 */
	float[] data = new float[18];

	// Drift Corrected Quaternion is calculated after steam input controller calibration values have been applied.
	// Rawest _useful_ version of a quaternion.
	// Most camera implementations should use this by comparing last rotation against current rotation, and applying the difference to the in game camera (plus your own sensitivity tweaks)
	// It is worth viewing 
	public float getDriftCorrectedQuatX() {
		return data[0];
	}
	public float getDriftCorrectedQuatY() {
		return data[1];
	}
	public float getDriftCorrectedQuatZ() {
		return data[2];
	}
	public float getDriftCorrectedQuatW() {
		return data[3];
	}

	// Sensor fusion corrects using accelerometer, and "average forward over time" for "forward".
	// This can "ouija" your aim, so it's not so  appropriate for camera controls (sensor fusion was originally made for racing game steering )
	// Same result as from old InputMotionData_t::rotQuatX/Y/Z/W
	public float getSensorFusionQuatX() {
		return data[4];
	}
	public float getSensorFusionQuatY() {
		return data[5];
	}
	public float getSensorFusionQuatZ() {
		return data[6];
	}
	public float getSensorFusionQuatW() {
		return data[7];
	}

	// Deferred Sensor fusion quaternion with deferred correction
	// Reduces perception of "ouija" effect by only applying correction when the controller is below "low noise" thresholds,
	// while the controller rotates fast - never when the user is attempting precision aim.
	public float getDeferredSensorFusionQuatX() {
		return data[8];
	}
	public float getDeferredSensorFusionQuatY() {
		return data[9];
	}
	public float getDeferredSensorFusionQuatZ() {
		return data[10];
	}
	public float getDeferredSensorFusionQuatW() {
		return data[11];
	}
	
	// Same as accel but values are calibrated such that 1 unit = 1G.
	// X = Right
	// Y = Forward out through the joystick USB port.
	// Z = Up through the joystick axis.
	public float getGravityX() {
		return data[12];
	}
	public float getGravityY() {
		return data[13];
	}
	public float getGravityZ() {
		return data[14];
	}

	// Same as rotVel values in GetMotionData but values are calibrated to degrees per second.
	// Local Space (controller relative)
	// X = Pitch = left to right axis
	// Y = Roll = axis through charging port
	// Z = Yaw = axis through sticks
	public float getDegreesPerSecondX() {
		return data[15];
	}
	public float getDegreesPerSecondY() {
		return data[16];
	}
	public float getDegreesPerSecondZ() {
		return data[17];
	}

}
