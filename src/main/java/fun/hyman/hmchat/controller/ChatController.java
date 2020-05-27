package fun.hyman.hmchat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import fun.hyman.hmchat.entity.ChatMsg;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Hyman
 * @date 2020年5月27日
 */
@Slf4j
@RestController
public class ChatController {
	
	@MessageMapping("/say")
	@SendTo("/topic/msg")
	public ChatMsg say(ChatMsg msg) {
		log.info(msg.toString());
		return msg;
	}
}
