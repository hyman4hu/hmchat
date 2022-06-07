package fun.hyman.hmchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fun.hyman.hmchat.common.R;
import fun.hyman.hmchat.dto.ChatMsgDTO;
import fun.hyman.hmchat.dto.HeartbeatDTO;
import fun.hyman.hmchat.service.IChatMsgService;
import fun.hyman.hmchat.service.UserInfoService;
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

    private final UserInfoService userInfoService;

    private final IChatMsgService chatMsgService;

//    @SendTo("/topic/msg")
	@MessageMapping("/say")
    public void say(@RequestBody ChatMsgDTO msg) {
        this.chatMsgService.save(msg);
        log.info("say msg - {}", msg.toString());
        messagingTemplate.convertAndSend("/topic/msg", msg);
//        Set<String> userIds = userInfoService.getUserIds();
//        for (String userId : userIds) {
//            log.info("dest - /{}/msg", userId);
//            messagingTemplate.convertAndSendToUser(userId, "/msg", msg);
//        }
    }

    /**
     * 心跳消息，同时统计在线信息
     * 
     * @param heartbeat
     */
    @MessageMapping("/heartbeat")
    public void heartbeat(@RequestBody HeartbeatDTO heartbeat, SimpMessageHeaderAccessor ha) {
        String ip = (String) ha.getSessionAttributes().get("ip");
        userInfoService.cache(heartbeat, ip);
    }

    /**
     * 获取所有消息，系统最多存储1000条消息
     */
    @GetMapping("/allmsgs")
    public R<?> allmsgs() {
        return R.ok(this.chatMsgService.all());
    }
}
