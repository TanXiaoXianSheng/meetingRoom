package webSocket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import net.sf.json.JSONObject;

@ServerEndpoint("/websocket")
public class ChatServer {

	private static SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private static Vector<Session> room = new Vector<Session>();
	
	@OnOpen
	public void onOpen(Session session) {
		room.addElement(session);
	}
	
	@OnMessage
	public void onMessage(String message,Session session) {
		JSONObject obj = JSONObject.fromObject(message);
		obj.put("data", df.format(new Date()));
		for(Session se:room) {
			obj.put("isSelf", se.equals(session));
			se.getAsyncRemote().sendText(obj.toString());
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		room.remove(session);
	}
	
	@OnError
	public void onError(Throwable t) {
		
	}
	
}
