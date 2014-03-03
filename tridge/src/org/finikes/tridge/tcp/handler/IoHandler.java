package org.finikes.tridge.tcp.handler;

import org.finikes.tridge.aio.tcp.session.SessionIdleType;
import org.finikes.tridge.aio.tcp.session.SocketSession;

public interface IoHandler {
	public void messageReceived(SocketSession session, Object msg);

	public void channelOpen(SocketSession session);

	public void channelClose(SocketSession session);

	public void exceptionCaught(SocketSession session, Throwable caught);

	public void channelIdle(SocketSession session, SessionIdleType idleType);
	
	public void messageSent(SocketSession session, Object response);
}