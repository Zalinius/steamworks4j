package com.codedisaster.steamworks;

public class SteamInput extends SteamInterface {
	//TODO focus on getting only input connect/disconnect callbacks working
	
	public static final int STEAM_INPUT_MAX_COUNT = 16;
	
	private final long[] inputHandles = new long[STEAM_INPUT_MAX_COUNT];

	public boolean init() {
		return SteamInputNative.init();
	}
	
	public int getConnectedControllers(SteamInputHandle[] handlesOut) {
		if (handlesOut.length < STEAM_INPUT_MAX_COUNT) {
			throw new IllegalArgumentException("Array size must be at least STEAM_INPUT_MAX_COUNT");
		}

		int count = SteamInputNative.getConnectedControllers(inputHandles);

		for (int i = 0; i < count; i++) {
			handlesOut[i] = new SteamInputHandle(inputHandles[i]);
		}

		return count;
	}

}
