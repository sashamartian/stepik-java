package chat;

import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

import javax.servlet.annotation.WebServlet;

/**
 * Chat servlet.
 */
@WebServlet(name = "WebSocketChatServlet", urlPatterns = {"/chat"})
public class WebSocketChatServlet extends WebSocketServlet {
    /**
     * LOGOUT_TIME.
     */
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    /**
     * ChatService.
     */
    private final ChatService chatService;

    /**
     * Chat servlet.
     */
    public WebSocketChatServlet() {
        this.chatService = new ChatService();
    }

    /**
     * configure.
     * @param factory factory
     */
    @Override
    public void configure(final WebSocketServletFactory factory) {
        factory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        factory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
