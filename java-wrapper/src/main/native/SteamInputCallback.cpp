#include "SteamInputCallback.h"

SteamInputCallback::SteamInputCallback(JNIEnv* env, jobject callback)
	: SteamCallbackAdapter(env, callback)
	, m_CallbackDeviceConnected(this, &SteamInputCallback::onDeviceConnected)
	, m_CallbackDeviceDisconnected(this, &SteamInputCallback::onDeviceDisconnected)
/*	, m_CallbackGameOverlayActivated(this, &SteamFriendsCallback::onGameOverlayActivated)
	, m_CallbackGameLobbyJoinRequested(this, &SteamFriendsCallback::onGameLobbyJoinRequested)
	, m_CallbackAvatarImageLoaded(this, &SteamFriendsCallback::onAvatarImageLoaded)
	, m_CallbackFriendRichPresenceUpdate(this, &SteamFriendsCallback::onFriendRichPresenceUpdate)
    , m_CallbackGameRichPresenceJoinRequested(this, &SteamFriendsCallback::onGameRichPresenceJoinRequested)
	, m_CallbackGameServerChangeRequested(this, &SteamFriendsCallback::onGameServerChangeRequested)*/ {

}

SteamInputCallback::~SteamInputCallback() {

}

void SteamInputCallback::onDeviceConnected(SteamInputDeviceConnected_t* callback, bool error) {
	invokeCallback({
		callVoidMethod(env, "onDeviceConnected", "(J)V", (jlong) callback->m_ulConnectedDeviceHandle);
	});
}

void SteamInputCallback::onDeviceDisconnected(SteamInputDeviceDisconnected_t* callback, bool error) {
	invokeCallback({
		callVoidMethod(env, "onDeviceDisconnected", "(J)V", (jlong) callback->m_ulDisconnectedDeviceHandle);
	});
}
