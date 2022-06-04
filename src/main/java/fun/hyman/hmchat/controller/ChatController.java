package fun.hyman.hmchat.controller;

import java.util.Set;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fun.hyman.hmchat.component.STOMPEventListener;
import fun.hyman.hmchat.entity.ChatMsg;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hyman
 * @date 2020年5月27日
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ChatController {

    /**
     * spring提供的推送方式
     */
    private final SimpMessagingTemplate messagingTemplate;

    private final STOMPEventListener stompEventListener;

//    @SendTo("/topic/msg")
	@MessageMapping("/say")
    public ChatMsg say(@RequestBody ChatMsg msg) {
        log.info("say msg - {}", msg.toString());
        Set<String> clientIds = stompEventListener.getClientIds();
        for(String clientId : clientIds) {
            log.info("dest - /{}/msg", clientId);
            if (clientId.equals(msg.getClientId())) {
                continue;
            }
            messagingTemplate.convertAndSendToUser(clientId, "/msg", msg);
        }
		return msg;
	}

}
