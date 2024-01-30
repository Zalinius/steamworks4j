package com.codedisaster.steamworks;

final class SteamInputNative {
	
	// @off

	/*JNI
		#include <steam_api.h>
		#include "isteaminput.h"
	*/

	static native boolean init(); /*
		return SteamInput()->Init(false);
	*/

	static native boolean shutdown(); /*
		return SteamInput()->Shutdown();
	*/

	static native int getConnectedControllers(long[] handlesOut); /*
		return SteamInput()->GetConnectedControllers((InputHandle_t*) handlesOut);
	*/

}
