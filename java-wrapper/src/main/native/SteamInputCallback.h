#pragma once

#include "SteamCallbackAdapter.h"
#include <steam_api.h>

class SteamInputCallback : public SteamCallbackAdapter {

public:
	SteamInputCallback(JNIEnv* env, jobject callback);
	~SteamInputCallback();

	STEAM_CALLBACK(SteamInputCallback, onDeviceConnected, SteamInputDeviceConnected_t, m_CallbackDeviceConnected);
	STEAM_CALLBACK(SteamInputCallback, onDeviceDisconnected, SteamInputDeviceDisconnected_t, m_CallbackDeviceDisconnected);

/*
	void onDeviceConnected(SteamInputDeviceConnected_t* callback, bool error);
	void onDeviceDisconnected(SteamInputDeviceDisconnected_t* callback, bool error);
*/
/*	void onSetPersonaNameResponse(SetPersonaNameResponse_t* callback, bool error);
	CCallResult<SteamFriendsCallback, SetPersonaNameResponse_t> onSetPersonaNameResponseCall;

	STEAM_CALLBACK(SteamFriendsCallback, onPersonaStateChange, PersonaStateChange_t, m_CallbackPersonaStateChange);
	STEAM_CALLBACK(SteamFriendsCallback, onGameOverlayActivated, GameOverlayActivated_t, m_CallbackGameOverlayActivated);
	STEAM_CALLBACK(SteamFriendsCallback, onGameLobbyJoinRequested, GameLobbyJoinRequested_t, m_CallbackGameLobbyJoinRequested);
	STEAM_CALLBACK(SteamFriendsCallback, onAvatarImageLoaded, AvatarImageLoaded_t, m_CallbackAvatarImageLoaded);
	STEAM_CALLBACK(SteamFriendsCallback, onFriendRichPresenceUpdate, FriendRichPresenceUpdate_t, m_CallbackFriendRichPresenceUpdate);
	STEAM_CALLBACK(SteamFriendsCallback, onGameRichPresenceJoinRequested, GameRichPresenceJoinRequested_t, m_CallbackGameRichPresenceJoinRequested);
	STEAM_CALLBACK(SteamFriendsCallback, onGameServerChangeRequested, GameServerChangeRequested_t, m_CallbackGameServerChangeRequested);*/
};
