package com.ebupt.ebms.cache;

public enum CacheIdPrefix {

	// TermInfo
	TerminalIdToGroupId,	
	TerminalsByGroupId,
	PhonesByTermianlId,
	
	TermianlIdExist,
	
	// RegisterInfo
	TerminalIdToRegisterData,
	TermSession,
	PasswdtoFactoryId,
	AuthFailedTimes,
	
	// TaskInfo
	TaskFlag,
	ResourceXMLPath,
	ResourceXMLPathMD5,
	ResourceXMLPathSize,

	// SelectTime
	TerminalSelectTime,
	
	//ShellTime
	TerminalShellTime,
	
	//onlinetime
	TerminalOnlineTime
	
}
