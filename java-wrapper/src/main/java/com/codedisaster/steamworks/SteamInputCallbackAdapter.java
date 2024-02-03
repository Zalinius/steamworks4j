package com.codedisaster.steamworks;

@SuppressWarnings("unused")
class SteamInputCallbackAdapter extends SteamCallbackAdapter<SteamInputCallback> {

	SteamInputCallbackAdapter(SteamInputCallback callback) {
		super(callback);
	}
	
	void onConfigurationLoaded(int appId, long deviceHandle, long mappingCreator, int majorRevision, int minorRevision, boolean usesSteamInputAPI, boolean usesGameAPI) {
		callback.onConfigurationLoaded(appId, new SteamInputHandle(deviceHandle), new SteamID(mappingCreator), majorRevision, minorRevision, usesSteamInputAPI, usesGameAPI);
	}
	
	void onDeviceConnected(long connectedDeviceHandle) {
		callback.onDeviceConnected(new SteamInputHandle(connectedDeviceHandle));
	}

	void onDeviceDisconnected(long disconnectedDeviceHandle) {
		callback.onDeviceDisconnected(new SteamInputHandle(disconnectedDeviceHandle));
	}
	
	void onGamepadSlotChange(int appId, long deviceHandle, SteamInput.Type deviceType, int oldGamepadSlot, int newGamepadSlot) {
		callback.onGamepadSlotChange(appId, new SteamInputHandle(deviceHandle), deviceType, oldGamepadSlot, newGamepadSlot);
	}
}
