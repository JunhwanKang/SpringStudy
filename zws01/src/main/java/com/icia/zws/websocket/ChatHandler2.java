package com.icia.zws.websocket;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatHandler2 extends TextWebSocketHandler {
	private List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		log.info("웹 소켓 연결: "+ session.getId());
		list.add(session);
	}
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		list.remove(session);
	}
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		TextMessage msg = new TextMessage(session.getPrincipal().getName()+ " : " +message.getPayload());
		for(WebSocketSession ws : list) {
			ws.sendMessage(msg);
		}
	}
}
