package com.codedisaster.steamworks;

final class SteamInputNative {
	
	// @off

	/*JNI
		#include <steam_api.h>
		#include "isteaminput.h"
		#include "SteamInputCallback.h"
	*/
	
	static native long createCallback(SteamInputCallbackAdapter javaCallback); /*
		return (intp) new SteamInputCallback(env, javaCallback);
	 */

	static native boolean init(boolean explicitlyCallRunFrame); /*
		return SteamInput()->Init(explicitlyCallRunFrame);
	*/

	static native boolean shutdown(); /*
		return SteamInput()->Shutdown();
	*/

	static native void runFrame(); /*
		SteamInput()->RunFrame();
	*/

	static native int getConnectedControllers(long[] handlesOut); /*
		return SteamInput()->GetConnectedControllers((InputHandle_t*) handlesOut);
	*/
	
	static native void enableDeviceCallbacks(); /*
		SteamInput()->EnableDeviceCallbacks();
	*/
	
	static native boolean showBindingPanel(long inputHandle); /*
		return SteamInput()->ShowBindingPanel((InputHandle_t) inputHandle);
	*/


	static native long getActionSetHandle(String actionSetName); /*
		return SteamInput()->GetActionSetHandle(actionSetName);
	*/
	
	static native void activateActionSet(long inputHandle, long actionSetHandle); /*
		SteamInput()->ActivateActionSet(inputHandle, actionSetHandle);
	*/

	static native long getDigitalActionHandle(String actionName); /*
		return SteamInput()->GetDigitalActionHandle(actionName);
	*/

	static native void getDigitalActionData(long inputHandle,
											long digitalActionHandle,
											SteamInputDigitalActionData digitalActionData); /*

		InputDigitalActionData_t result = SteamInput()->GetDigitalActionData(
			(InputHandle_t) inputHandle, (InputDigitalActionHandle_t) digitalActionHandle);

		{
			jclass clazz = env->GetObjectClass(digitalActionData);

			jfieldID field = env->GetFieldID(clazz, "state", "Z");
			env->SetBooleanField(digitalActionData, field, (jboolean) result.bState);

			field = env->GetFieldID(clazz, "active", "Z");
			env->SetBooleanField(digitalActionData, field, (jboolean) result.bActive);
		}
	*/


}
