package fun.hyman.hmchat.controller;

import java.util.Set;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.hyman.hmchat.dto.ChatMsgDTO;
import fun.hyman.hmchat.dto.HeartbeatDTO;
import fun.hyman.hmchat.service.ClientInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hyman
 * @date 2020年5月27日
 */
@Slf4j
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    /**
     * spring提供的推送方式
     */
    private final SimpMessagingTemplate messagingTemplate;

    private final ClientInfoService clientInfoService;

//    @SendTo("/topic/msg")
	@MessageMapping("/say")
    public void say(@RequestBody ChatMsgDTO msg) {
        log.info("say msg - {}", msg.toString());
        Set<String> clientIds = clientInfoService.getClientIds();
        for (String clientId : clientIds) {
            log.info("dest - /{}/msg", clientId);
            if (clientId.equals(msg.getClientId())) {
                continue;
            }
            messagingTemplate.convertAndSendToUser(clientId, "/msg", msg);
        }
    }

    /**
     * 心跳消息，同时统计在线信息
     * 
     * @param heartbeat
     */
    @MessageMapping("/heartbeat")
    public void heartbeat(@RequestBody HeartbeatDTO heartbeat, SimpMessageHeaderAccessor ha) {
        String ip = (String) ha.getSessionAttributes().get("ip");
        log.info("heartbeat - {}", heartbeat.toString());
        clientInfoService.cache(heartbeat, ip);
    }

}
