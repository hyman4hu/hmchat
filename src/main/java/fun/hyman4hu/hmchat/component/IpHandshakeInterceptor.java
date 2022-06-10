package fun.hyman4hu.hmchat.component;

import java.util.Map;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * 获取真实IP
 * 
 * @author Hyman
 * @date 2022/06/05
 */
public class IpHandshakeInterceptor implements HandshakeInterceptor {

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Map<String, Object> attributes) throws Exception {
        // Set ip attribute to WebSocket session
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            String ipAddress = servletRequest.getServletRequest().getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = servletRequest.getServletRequest().getRemoteAddr();
            }
            attributes.put("ip", ipAddress);
        }
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
            Exception exception) {

    }

}
