package chat;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ChatService.
 */
public class ChatService {
    /**
     * webSockets.
     */
    private Set<ChatWebSocket> webSockets;

    /**
     * ChatService.
     */
    public ChatService() {
        this.webSockets = Collections.newSetFromMap(new ConcurrentHashMap<>());
    }

    /**
     * sendMessage.
     * @param data string
     */
    public void sendMessage(final String data) {
        for (ChatWebSocket user : webSockets) {
            try {
                user.sendString(data);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * add.
     * @param webSocket webSocket
     */
    public void add(final ChatWebSocket webSocket) {
        webSockets.add(webSocket);
    }

    /**
     * remove.
     * @param webSocket webSocket
     */
    public void remove(final ChatWebSocket webSocket) {
        webSockets.remove(webSocket);
    }

}
