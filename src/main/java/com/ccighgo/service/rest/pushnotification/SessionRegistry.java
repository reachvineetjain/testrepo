package com.ccighgo.service.rest.pushnotification;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public enum SessionRegistry {
	INSTANCE;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionRegistry.class);

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
		if(sessions.containsKey(uid)) {
			return sessions.get(uid);
		}
		LOGGER.error("WebSocket session N/A for " + uid);
		throw new RuntimeException("Key " + uid + "N/A.");
	}

	public void addSession(String uid, Session session) {
		Preconditions.checkNotNull(session);
		Preconditions.checkNotNull(uid);
		Preconditions.checkArgument(!uid.isEmpty());
		
		System.out.println("Adding new session for :: " + uid + " :: " + session.getId());
		LOGGER.info("New websocket connection opened for " + uid + ", sessionId = " + session.getId());
		sessionIdMap.put(session.getId(), uid);
		sessions.put(uid, session);
	}

	public void removeSession(Session session) {
		Preconditions.checkNotNull(session);
		
		String uid = sessionIdMap.get(session.getId());
		if(uid != null && !uid.isEmpty()) {
			sessions.remove(uid);
			sessionIdMap.remove(session.getId());
			LOGGER.info("WebSocket session removed for " + uid + ", sessionId = " + session.getId());
		} else {
			LOGGER.info("Could not remove WebSocket session. uid N/A for sessionId = " + session.getId());
		}
		
		System.out.println("Session removed for :: " + uid + " :: " + session.getId());
	}
}
