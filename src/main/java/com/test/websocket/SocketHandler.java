package com.test.websocket;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class SocketHandler extends TextWebSocketHandler implements InitializingBean {
	//private final Logger logger = LogManager.getLogger(getClass());
	private List<WebSocketSession> list;

	public SocketHandler() {
		super();
		list = new ArrayList<WebSocketSession>();
		//this.logger.info("create SocketHandler instance!");
		System.out.println("create SocketHandler instance!");
	}

	// WebSocket 연결이 열리고 사용이 준비될 때 호출
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		super.afterConnectionEstablished(session);

		list.add(session);
		//this.logger.info("add session!");
		System.out.println(session.getRemoteAddress().getHostName());
		System.out.println("add session!");
	}

	// 클라이언트로부터 메시지가 도착햇을 때 호출
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		// TODO Auto-generated method stub
		super.handleTextMessage(session, message);

		//this.logger.info("receive message:" + message.toString());
		System.out.println("receive message:" + message.toString());
		session.sendMessage(new TextMessage(message.getPayload()));
	}

	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {
		//this.logger.error("web socket error!", exception);
		System.out.println("web socket error!" + exception);
	}

	@Override
	public boolean supportsPartialMessages() {
		//this.logger.info("call method!");
		System.out.println("call method!");
		return super.supportsPartialMessages();
	}

	public void sendMessage(String message) {
		for (WebSocketSession session : this.list) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (Exception ignored) {
					//this.logger.error("fail to send message!", ignored);
					System.out.println("fail to send message!"+ ignored);
				}
			}
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		sendMessage("send message index ");
		/*Thread thread = new Thread() {

			int i = 0;

			@Override
			public void run() {
				while (true) {

					try {
						sendMessage("send message index " + i++);
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
						break;
					}
				}
			}

		};

		thread.start();*/
	}

	// 연결이 닫혔을 때 호출
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		super.afterConnectionClosed(session, status);

		list.remove(session);
		//this.logger.info("remove session!");
		
		System.out.println("remove session!");
	}

}