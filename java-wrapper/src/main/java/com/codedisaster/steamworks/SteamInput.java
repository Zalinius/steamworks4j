package com.codedisaster.steamworks;

public class SteamInput extends SteamInterface {
	//TODO focus on getting only input connect/disconnect callbacks working
	
	public static final int STEAM_INPUT_MAX_COUNT = 16;
	public static final int STEAM_INPUT_MAX_ANALOG_ACTIONS = 24;
	public static final int STEAM_INPUT_MAX_DIGITAL_ACTIONS = 256;
	public static final int STEAM_INPUT_MAX_ORIGINS = 8;
	public static final int STEAM_INPUT_MAX_ACTIVE_LAYERS = 16;
	
	public static final long STEAM_CONTROLLER_HANDLE_ALL_CONTROLLERS = 0xffffffffffffffffL;

	public static final float STEAM_CONTROLLER_MIN_ANALOG_ACTION_DATA = -1.0f;
	public static final float STEAM_CONTROLLER_MAX_ANALOG_ACTION_DATA = 1.0f;

	
	private final long[] inputHandles = new long[STEAM_INPUT_MAX_COUNT];
	
	public SteamInput(SteamInputCallback callback) {
		super(SteamInputNative.createCallback(new SteamInputCallbackAdapter(callback)));
	}
	

	public boolean init(boolean explicitlyCallRunFrame) {
		return SteamInputNative.init(explicitlyCallRunFrame);
	}

	public boolean shutdown() {
		return SteamInputNative.shutdown();
	}

	public void runFrame() {
		SteamInputNative.runFrame();
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
	
	public void enableDeviceCallbacks() {
		SteamInputNative.enableDeviceCallbacks();
	}
	
	public boolean showBindingPanel(SteamInputHandle input) {
		return SteamInputNative.showBindingPanel(input.handle);
	}
	
	public SteamInputActionSetHandle getActionSetHandle(String actionSetName) {
		return new SteamInputActionSetHandle(SteamInputNative.getActionSetHandle(actionSetName));
	}

	public void activateActionSet(SteamInputHandle inputHandle, SteamInputActionSetHandle actionSetHandle) {
		SteamInputNative.activateActionSet(inputHandle.handle, actionSetHandle.handle);
	}
	
	public SteamInputDigitalActionHandle getDigitalActionHandle(String actionName) {
		return new SteamInputDigitalActionHandle(SteamInputNative.getDigitalActionHandle(actionName));
	}
	
	public void getDigitalActionData(SteamInputHandle input,
			 SteamInputDigitalActionHandle digitalAction,
			 SteamInputDigitalActionData digitalActionData) {
		SteamInputNative.getDigitalActionData(input.handle, digitalAction.handle, digitalActionData);
	}

	

	public enum SourceMode {
		None,
		Dpad,
		Buttons,
		FourButtons,
		AbsoluteMouse,
		RelativeMouse,
		JoystickMove,
		JoystickMouse,
		JoystickCamera,
		ScrollWheel,
		Trigger,
		TouchMenu,
		MouseJoystick,
		MouseRegion,
		RadialMenu,
		SingleButton,
		Switches;

		private static final SourceMode[] values = values();

		static SourceMode byOrdinal(int ordinal) {
			return values[ordinal];
		}
	}
	
	public enum ActionOrigin {
		// Steam Controller
		None,
		SteamController_A,
		SteamController_B,
		SteamController_X,
		SteamController_Y,
		SteamController_LeftBumper,
		SteamController_RightBumper,
		SteamController_LeftGrip,
		SteamController_RightGrip,
		SteamController_Start,
		SteamController_Back,
		SteamController_LeftPad_Touch,
		SteamController_LeftPad_Swipe,
		SteamController_LeftPad_Click,
		SteamController_LeftPad_DPadNorth,
		SteamController_LeftPad_DPadSouth,
		SteamController_LeftPad_DPadWest,
		SteamController_LeftPad_DPadEast,
		SteamController_RightPad_Touch,
		SteamController_RightPad_Swipe,
		SteamController_RightPad_Click,
		SteamController_RightPad_DPadNorth,
		SteamController_RightPad_DPadSouth,
		SteamController_RightPad_DPadWest,
		SteamController_RightPad_DPadEast,
		SteamController_LeftTrigger_Pull,
		SteamController_LeftTrigger_Click,
		SteamController_RightTrigger_Pull,
		SteamController_RightTrigger_Click,
		SteamController_LeftStick_Move,
		SteamController_LeftStick_Click,
		SteamController_LeftStick_DPadNorth,
		SteamController_LeftStick_DPadSouth,
		SteamController_LeftStick_DPadWest,
		SteamController_LeftStick_DPadEast,
		SteamController_Gyro_Move,
		SteamController_Gyro_Pitch,
		SteamController_Gyro_Yaw,
		SteamController_Gyro_Roll,
		SteamController_Reserved0,
		SteamController_Reserved1,
		SteamController_Reserved2,
		SteamController_Reserved3,
		SteamController_Reserved4,
		SteamController_Reserved5,
		SteamController_Reserved6,
		SteamController_Reserved7,
		SteamController_Reserved8,
		SteamController_Reserved9,
		SteamController_Reserved10,
		
		// PS4 Dual Shock
		PS4_X,
		PS4_Circle,
		PS4_Triangle,
		PS4_Square,
		PS4_LeftBumper,
		PS4_RightBumper,
		PS4_Options,	//Start
		PS4_Share,		//Back
		PS4_LeftPad_Touch,
		PS4_LeftPad_Swipe,
		PS4_LeftPad_Click,
		PS4_LeftPad_DPadNorth,
		PS4_LeftPad_DPadSouth,
		PS4_LeftPad_DPadWest,
		PS4_LeftPad_DPadEast,
		PS4_RightPad_Touch,
		PS4_RightPad_Swipe,
		PS4_RightPad_Click,
		PS4_RightPad_DPadNorth,
		PS4_RightPad_DPadSouth,
		PS4_RightPad_DPadWest,
		PS4_RightPad_DPadEast,
		PS4_CenterPad_Touch,
		PS4_CenterPad_Swipe,
		PS4_CenterPad_Click,
		PS4_CenterPad_DPadNorth,
		PS4_CenterPad_DPadSouth,
		PS4_CenterPad_DPadWest,
		PS4_CenterPad_DPadEast,
		PS4_LeftTrigger_Pull,
		PS4_LeftTrigger_Click,
		PS4_RightTrigger_Pull,
		PS4_RightTrigger_Click,
		PS4_LeftStick_Move,
		PS4_LeftStick_Click,
		PS4_LeftStick_DPadNorth,
		PS4_LeftStick_DPadSouth,
		PS4_LeftStick_DPadWest,
		PS4_LeftStick_DPadEast,
		PS4_RightStick_Move,
		PS4_RightStick_Click,
		PS4_RightStick_DPadNorth,
		PS4_RightStick_DPadSouth,
		PS4_RightStick_DPadWest,
		PS4_RightStick_DPadEast,
		PS4_DPad_North,
		PS4_DPad_South,
		PS4_DPad_West,
		PS4_DPad_East,
		PS4_Gyro_Move,
		PS4_Gyro_Pitch,
		PS4_Gyro_Yaw,
		PS4_Gyro_Roll,
		PS4_DPad_Move,
		PS4_Reserved1,
		PS4_Reserved2,
		PS4_Reserved3,
		PS4_Reserved4,
		PS4_Reserved5,
		PS4_Reserved6,
		PS4_Reserved7,
		PS4_Reserved8,
		PS4_Reserved9,
		PS4_Reserved10,

		// XBox One
		XBoxOne_A,
		XBoxOne_B,
		XBoxOne_X,
		XBoxOne_Y,
		XBoxOne_LeftBumper,
		XBoxOne_RightBumper,
		XBoxOne_Menu,  //Start
		XBoxOne_View,  //Back
		XBoxOne_LeftTrigger_Pull,
		XBoxOne_LeftTrigger_Click,
		XBoxOne_RightTrigger_Pull,
		XBoxOne_RightTrigger_Click,
		XBoxOne_LeftStick_Move,
		XBoxOne_LeftStick_Click,
		XBoxOne_LeftStick_DPadNorth,
		XBoxOne_LeftStick_DPadSouth,
		XBoxOne_LeftStick_DPadWest,
		XBoxOne_LeftStick_DPadEast,
		XBoxOne_RightStick_Move,
		XBoxOne_RightStick_Click,
		XBoxOne_RightStick_DPadNorth,
		XBoxOne_RightStick_DPadSouth,
		XBoxOne_RightStick_DPadWest,
		XBoxOne_RightStick_DPadEast,
		XBoxOne_DPad_North,
		XBoxOne_DPad_South,
		XBoxOne_DPad_West,
		XBoxOne_DPad_East,
		XBoxOne_DPad_Move,
		XBoxOne_LeftGrip_Lower,
		XBoxOne_LeftGrip_Upper,
		XBoxOne_RightGrip_Lower,
		XBoxOne_RightGrip_Upper,
		XBoxOne_Share, // Xbox Series X controllers only
		XBoxOne_Reserved6,
		XBoxOne_Reserved7,
		XBoxOne_Reserved8,
		XBoxOne_Reserved9,
		XBoxOne_Reserved10,

		// XBox 360
		XBox360_A,
		XBox360_B,
		XBox360_X,
		XBox360_Y,
		XBox360_LeftBumper,
		XBox360_RightBumper,
		XBox360_Start,		//Start
		XBox360_Back,		//Back
		XBox360_LeftTrigger_Pull,
		XBox360_LeftTrigger_Click,
		XBox360_RightTrigger_Pull,
		XBox360_RightTrigger_Click,
		XBox360_LeftStick_Move,
		XBox360_LeftStick_Click,
		XBox360_LeftStick_DPadNorth,
		XBox360_LeftStick_DPadSouth,
		XBox360_LeftStick_DPadWest,
		XBox360_LeftStick_DPadEast,
		XBox360_RightStick_Move,
		XBox360_RightStick_Click,
		XBox360_RightStick_DPadNorth,
		XBox360_RightStick_DPadSouth,
		XBox360_RightStick_DPadWest,
		XBox360_RightStick_DPadEast,
		XBox360_DPad_North,
		XBox360_DPad_South,
		XBox360_DPad_West,
		XBox360_DPad_East,	
		XBox360_DPad_Move,
		XBox360_Reserved1,
		XBox360_Reserved2,
		XBox360_Reserved3,
		XBox360_Reserved4,
		XBox360_Reserved5,
		XBox360_Reserved6,
		XBox360_Reserved7,
		XBox360_Reserved8,
		XBox360_Reserved9,
		XBox360_Reserved10,


		// Switch - Pro or Joycons used as a single input device.
		// This does not apply to a single joycon
		Switch_A,
		Switch_B,
		Switch_X,
		Switch_Y,
		Switch_LeftBumper,
		Switch_RightBumper,
		Switch_Plus,	//Start
		Switch_Minus,	//Back
		Switch_Capture,
		Switch_LeftTrigger_Pull,
		Switch_LeftTrigger_Click,
		Switch_RightTrigger_Pull,
		Switch_RightTrigger_Click,
		Switch_LeftStick_Move,
		Switch_LeftStick_Click,
		Switch_LeftStick_DPadNorth,
		Switch_LeftStick_DPadSouth,
		Switch_LeftStick_DPadWest,
		Switch_LeftStick_DPadEast,
		Switch_RightStick_Move,
		Switch_RightStick_Click,
		Switch_RightStick_DPadNorth,
		Switch_RightStick_DPadSouth,
		Switch_RightStick_DPadWest,
		Switch_RightStick_DPadEast,
		Switch_DPad_North,
		Switch_DPad_South,
		Switch_DPad_West,
		Switch_DPad_East,
		Switch_ProGyro_Move,  // Primary Gyro in Pro Controller, or Right JoyCon
		Switch_ProGyro_Pitch,  // Primary Gyro in Pro Controller, or Right JoyCon
		Switch_ProGyro_Yaw,  // Primary Gyro in Pro Controller, or Right JoyCon
		Switch_ProGyro_Roll,  // Primary Gyro in Pro Controller, or Right JoyCon
		Switch_DPad_Move,
		Switch_Reserved1,
		Switch_Reserved2,
		Switch_Reserved3,
		Switch_Reserved4,
		Switch_Reserved5,
		Switch_Reserved6,
		Switch_Reserved7,
		Switch_Reserved8,
		Switch_Reserved9,
		Switch_Reserved10,

		// Switch JoyCon Specific
		Switch_RightGyro_Move,  // Right JoyCon Gyro generally should correspond to Pro's single gyro
		Switch_RightGyro_Pitch,  // Right JoyCon Gyro generally should correspond to Pro's single gyro
		Switch_RightGyro_Yaw,  // Right JoyCon Gyro generally should correspond to Pro's single gyro
		Switch_RightGyro_Roll,  // Right JoyCon Gyro generally should correspond to Pro's single gyro
		Switch_LeftGyro_Move,
		Switch_LeftGyro_Pitch,
		Switch_LeftGyro_Yaw,
		Switch_LeftGyro_Roll,
		Switch_LeftGrip_Lower, // Left JoyCon SR Button
		Switch_LeftGrip_Upper, // Left JoyCon SL Button
		Switch_RightGrip_Lower,  // Right JoyCon SL Button
		Switch_RightGrip_Upper,  // Right JoyCon SR Button
		Switch_JoyConButton_N, // With a Horizontal JoyCon this will be Y or what would be Dpad Right when vertical
		Switch_JoyConButton_E, // X
		Switch_JoyConButton_S, // A
		Switch_JoyConButton_W, // B
		Switch_Reserved15,
		Switch_Reserved16,
		Switch_Reserved17,
		Switch_Reserved18,
		Switch_Reserved19,
		Switch_Reserved20,
		
		// Added in SDK 1.51
		PS5_X,
		PS5_Circle,
		PS5_Triangle,
		PS5_Square,
		PS5_LeftBumper,
		PS5_RightBumper,
		PS5_Option,	//Start
		PS5_Create,		//Back
		PS5_Mute,
		PS5_LeftPad_Touch,
		PS5_LeftPad_Swipe,
		PS5_LeftPad_Click,
		PS5_LeftPad_DPadNorth,
		PS5_LeftPad_DPadSouth,
		PS5_LeftPad_DPadWest,
		PS5_LeftPad_DPadEast,
		PS5_RightPad_Touch,
		PS5_RightPad_Swipe,
		PS5_RightPad_Click,
		PS5_RightPad_DPadNorth,
		PS5_RightPad_DPadSouth,
		PS5_RightPad_DPadWest,
		PS5_RightPad_DPadEast,
		PS5_CenterPad_Touch,
		PS5_CenterPad_Swipe,
		PS5_CenterPad_Click,
		PS5_CenterPad_DPadNorth,
		PS5_CenterPad_DPadSouth,
		PS5_CenterPad_DPadWest,
		PS5_CenterPad_DPadEast,
		PS5_LeftTrigger_Pull,
		PS5_LeftTrigger_Click,
		PS5_RightTrigger_Pull,
		PS5_RightTrigger_Click,
		PS5_LeftStick_Move,
		PS5_LeftStick_Click,
		PS5_LeftStick_DPadNorth,
		PS5_LeftStick_DPadSouth,
		PS5_LeftStick_DPadWest,
		PS5_LeftStick_DPadEast,
		PS5_RightStick_Move,
		PS5_RightStick_Click,
		PS5_RightStick_DPadNorth,
		PS5_RightStick_DPadSouth,
		PS5_RightStick_DPadWest,
		PS5_RightStick_DPadEast,
		PS5_DPad_North,
		PS5_DPad_South,
		PS5_DPad_West,
		PS5_DPad_East,
		PS5_Gyro_Move,
		PS5_Gyro_Pitch,
		PS5_Gyro_Yaw,
		PS5_Gyro_Roll,
		PS5_DPad_Move,
		PS5_LeftGrip,
		PS5_RightGrip,
		PS5_LeftFn,
		PS5_RightFn,
		PS5_Reserved5,
		PS5_Reserved6,
		PS5_Reserved7,
		PS5_Reserved8,
		PS5_Reserved9,
		PS5_Reserved10,
		PS5_Reserved11,
		PS5_Reserved12,
		PS5_Reserved13,
		PS5_Reserved14,
		PS5_Reserved15,
		PS5_Reserved16,
		PS5_Reserved17,
		PS5_Reserved18,
		PS5_Reserved19,
		PS5_Reserved20,

		// Added in SDK 1.53
		SteamDeck_A,
		SteamDeck_B,
		SteamDeck_X,
		SteamDeck_Y,
		SteamDeck_L1,
		SteamDeck_R1,
		SteamDeck_Menu,
		SteamDeck_View,
		SteamDeck_LeftPad_Touch,
		SteamDeck_LeftPad_Swipe,
		SteamDeck_LeftPad_Click,
		SteamDeck_LeftPad_DPadNorth,
		SteamDeck_LeftPad_DPadSouth,
		SteamDeck_LeftPad_DPadWest,
		SteamDeck_LeftPad_DPadEast,
		SteamDeck_RightPad_Touch,
		SteamDeck_RightPad_Swipe,
		SteamDeck_RightPad_Click,
		SteamDeck_RightPad_DPadNorth,
		SteamDeck_RightPad_DPadSouth,
		SteamDeck_RightPad_DPadWest,
		SteamDeck_RightPad_DPadEast,
		SteamDeck_L2_SoftPull,
		SteamDeck_L2,
		SteamDeck_R2_SoftPull,
		SteamDeck_R2,
		SteamDeck_LeftStick_Move,
		SteamDeck_L3,
		SteamDeck_LeftStick_DPadNorth,
		SteamDeck_LeftStick_DPadSouth,
		SteamDeck_LeftStick_DPadWest,
		SteamDeck_LeftStick_DPadEast,
		SteamDeck_LeftStick_Touch,
		SteamDeck_RightStick_Move,
		SteamDeck_R3,
		SteamDeck_RightStick_DPadNorth,
		SteamDeck_RightStick_DPadSouth,
		SteamDeck_RightStick_DPadWest,
		SteamDeck_RightStick_DPadEast,
		SteamDeck_RightStick_Touch,
		SteamDeck_L4,
		SteamDeck_R4,
		SteamDeck_L5,
		SteamDeck_R5,
		SteamDeck_DPad_Move,
		SteamDeck_DPad_North,
		SteamDeck_DPad_South,
		SteamDeck_DPad_West,
		SteamDeck_DPad_East,
		SteamDeck_Gyro_Move,
		SteamDeck_Gyro_Pitch,
		SteamDeck_Gyro_Yaw,
		SteamDeck_Gyro_Roll,
		SteamDeck_Reserved1,
		SteamDeck_Reserved2,
		SteamDeck_Reserved3,
		SteamDeck_Reserved4,
		SteamDeck_Reserved5,
		SteamDeck_Reserved6,
		SteamDeck_Reserved7,
		SteamDeck_Reserved8,
		SteamDeck_Reserved9,
		SteamDeck_Reserved10,
		SteamDeck_Reserved11,
		SteamDeck_Reserved12,
		SteamDeck_Reserved13,
		SteamDeck_Reserved14,
		SteamDeck_Reserved15,
		SteamDeck_Reserved16,
		SteamDeck_Reserved17,
		SteamDeck_Reserved18,
		SteamDeck_Reserved19,
		SteamDeck_Reserved20;

		private static final ActionOrigin[] values = values();

		static ActionOrigin byOrdinal(int ordinal) {
			return values[ordinal];
		}
	}
	
	public enum XboxOrigin {
		A,
		B,
		X,
		Y,
		LeftBumper,
		RightBumper,
		Menu,  //Start
		View,  //Back
		LeftTrigger_Pull,
		LeftTrigger_Click,
		RightTrigger_Pull,
		RightTrigger_Click,
		LeftStick_Move,
		LeftStick_Click,
		LeftStick_DPadNorth,
		LeftStick_DPadSouth,
		LeftStick_DPadWest,
		LeftStick_DPadEast,
		RightStick_Move,
		RightStick_Click,
		RightStick_DPadNorth,
		RightStick_DPadSouth,
		RightStick_DPadWest,
		RightStick_DPadEast,
		DPad_North,
		DPad_South,
		DPad_West,
		DPad_East
	}
	
	public enum SteamControllerPad {
		Left,
		Right;
	}
	
	public enum ControllerHapticLocation {
		Left ( 1 << SteamControllerPad.Left.ordinal() ),
		Right( 1 << SteamControllerPad.Right.ordinal()),
		Both ( 1 << SteamControllerPad.Left.ordinal() | 1 << SteamControllerPad.Right.ordinal() );
		
		private ControllerHapticLocation(int value) {
			this.value = value;
		}
		private int value;
		
		public int getValue() {
			return value;
		}
	}
	
	public enum ControllerHapticType {
		Off,
		Tick,
		Click,
	}
	
	public enum Type {
		Unknown,
		SteamController,
		XBox360Controller,
		XBoxOneController,
		GenericGamepad,		// DirectInput controllers
		PS4Controller,
		AppleMFiController,	// Unused
		AndroidController,	// Unused
		SwitchJoyConPair,		// Unused
		SwitchJoyConSingle,	// Unused
		SwitchProController,
		MobileTouch,			// Steam Link App On-screen Virtual Controller
		PS3Controller,		// Currently uses PS4 Origins
		PS5Controller,		// Added in SDK 151
		SteamDeckController,	// Added in SDK 153
	}

	// Individual values are used by the GetSessionInputConfigurationSettings bitmask
	public enum ConfigurationEnableType
	{
		None(0x0000),
		Playstation(0x0001),
		Xbox(0x0002),
		Generic(0x0004),
		Switch(0x0008);
		
		private final int mask;
		
		private ConfigurationEnableType(int mask) {
			this.mask = mask;
		}
		public int getMask() {
			return mask;
		}	
	}

	// These values are passed into SetLEDColor
	public enum LEDFlag	{
		SetColor,
		// Restore the LED color to the user's preference setting as set in the controller personalization menu.
		// This also happens automatically on exit of your game.  
		RestoreUserDefault 
	}
	
	public enum GlyphStyle {
		// Base-styles - cannot mix
		Knockout(0x0), // Face buttons will have colored labels/outlines on a knocked out background
		// Rest of inputs will have white detail/borders on a knocked out background
		Light(0x1), // Black detail/borders on a white background
		Dark(0x2), // White detail/borders on a black background
	
		// Modifiers
		// Default ABXY/PS equivalent glyphs have a solid fill w/ color matching the physical buttons on the device
		NeutralColorABXY(0x10), // ABXY Buttons will match the base style color instead of their normal associated color
		SolidABXY(0x20);	// ABXY Buttons will have a solid fill
		
		private final int value;
		
		private GlyphStyle(int value) {
			this.value = value;
		}
		
		public int getValue() {
			return value;
		}
	}
	
	public enum ActionEventType {
		DigitalAction,
		AnalogAction;
	}


}
