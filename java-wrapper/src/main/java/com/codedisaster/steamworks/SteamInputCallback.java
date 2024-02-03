package com.codedisaster.steamworks;

public interface SteamInputCallback {
	
	default void onConfigurationLoaded(int appId, SteamInputHandle inputHandle, SteamID mappingCreator, int majorRevision, int minorRevision, boolean usesSteamInputAPI, boolean usesGameAPI) {}

	default void onDeviceConnected(SteamInputHandle inputHandle) {}
	
	default void onDeviceDisconnected(SteamInputHandle inputHandle) {}

	default void onGamepadSlotChange(int appId, SteamInputHandle inputHandle, SteamInput.Type deviceType, int oldGamepadSlot, int newGamepadSlot) {}

}
