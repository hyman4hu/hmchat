package fun.hyman.hmchat.component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author huyin3
 * @date 2022/06/04
 */
@Slf4j
@Component
public class STOMPEventListener implements ApplicationListener<AbstractSubProtocolEvent> {

    private final static Object lock = new Object();

//    private Cache<String, String> userCache = CacheUtil.newTimedCache(24 * 60 * 60 * 1000);

    private Map<String, String> mapSessionUser = new HashMap<>();// 根据session找用户

    private Map<String, Integer> mapUserNum = new HashMap<>();// 保存用户连接数量，一个用户可能会打开多个标签页，建立多个连接

    public Set<String> getClientIds() {
        return mapUserNum.keySet();
    }

    @Override
    public void onApplicationEvent(AbstractSubProtocolEvent event) {
        if (event instanceof SessionConnectEvent) {
            StompHeaderAccessor sha = StompHeaderAccessor.wrap(((SessionConnectEvent) event).getMessage());
            String sessionId = sha.getSessionId();
            String clientId = sha.getNativeHeader("clientId").get(0);
            synchronized (lock) {
                mapSessionUser.put(sessionId, clientId);
                Integer num = mapUserNum.get(clientId);
                if (num == null) {
                    num = 0;
                }
                num++;
                mapUserNum.put(clientId, num);
            }
            log.info("用户{}连接到服务器，当前在线用户数：{}", clientId, mapUserNum.keySet().size());
        } else if (event instanceof SessionDisconnectEvent) {
            StompHeaderAccessor sha = StompHeaderAccessor.wrap(((SessionDisconnectEvent) event).getMessage());
            String sessionId = sha.getSessionId();
            String clientId = mapSessionUser.get(sessionId);
            synchronized (lock) {
                mapSessionUser.remove(sessionId);
                Integer num = mapUserNum.get(clientId);
                if (num != null) {
                    num--;
                    if (num <= 0) {
                        mapUserNum.remove(clientId);
                    }
                }
            }
            log.info("用户{}离开服务器，当前在线用户数：{}", clientId, mapUserNum.keySet().size());
        }
    }

}
