package com.codedisaster.steamworks;

@SuppressWarnings("WeakerAccess")
@Deprecated
public class SteamControllerAnalogActionData {

	int mode;
	float x, y;
	boolean active;

	public SteamController.SourceMode getMode() {
		return SteamController.SourceMode.byOrdinal(mode);
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public boolean getActive() {
		return active;
	}

}
