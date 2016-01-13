package com.ccighgo.service.rest.pushnotification;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public enum SessionRegistry {
	INSTANCE;

	// uid -> Session
	private final Map<String, Session> sessions;

	// sessionId -> uid
	private final Map<String, String> sessionIdMap;

	private SessionRegistry() {
		sessions = Collections.synchronizedMap(new HashMap<>());
		sessionIdMap = Collections.synchronizedMap(new HashMap<>());
	}

	public Map<String, Session> getSessions() {
		return Collections.unmodifiableMap(sessions);
	}

	public Session getClientSession(String uid) {
		return sessions.get(uid);
	}

	public void addSession(String uid, Session session) {
		System.out.println("Adding new session for :: " + uid + " :: " + session.getId());
		sessionIdMap.put(session.getId(), uid);
		sessions.put(uid, session);
	}

	public void removeSession(Session session) {
		String uid = sessionIdMap.get(session.getId());
		sessions.remove(uid);
		System.out.println("");
		System.out.println("Session removed for :: " + uid + " :: " + session.getId());
	}

}
