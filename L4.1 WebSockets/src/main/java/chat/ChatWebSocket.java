package chat;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

/**
 * ChatWebSocket.
 */
@SuppressWarnings("UnusedDeclaration")
@WebSocket
public class ChatWebSocket {
    /**
     * chatService.
     */
    private ChatService chatService;

    /**
     * session.
     */
    private Session session;

    /**
     * ChatWebSocket.
     * @param service chatService
     */
    public ChatWebSocket(final ChatService service) {
        this.chatService = service;
    }

    /**
     * onOpen.
     * @param userSession session
     */
    @OnWebSocketConnect
    public void onOpen(final Session userSession) {
        chatService.add(this);
        this.session = userSession;
    }

    /**
     * onMessage.
     * @param data data
     */
    @OnWebSocketMessage
    public void onMessage(final String data) {
        chatService.sendMessage(data);
    }

    /**
     * onClose.
     * @param statusCode statusCode
     * @param reason reason
     */
    @OnWebSocketClose
    public void onClose(final int statusCode,
                        final String reason) {
        chatService.remove(this);
    }

    /**
     * sendString.
     * @param data data
     */
    public void sendString(final String data) {
        try {
            session.getRemote().sendString(data);
            session.getRemote().sendString(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
