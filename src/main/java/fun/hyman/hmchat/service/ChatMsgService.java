package fun.hyman.hmchat.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import fun.hyman.hmchat.dto.ChatMsgDTO;

/**
 * 消息服务
 * 
 * @author huyin3
 * @date 2022/06/05
 */
@Service
public class ChatMsgService {

    private List<ChatMsgDTO> cacheMsgs = Collections.synchronizedList(new ArrayList<>());

    public void save(ChatMsgDTO msg) {
        cacheMsgs.add(msg);
        if (cacheMsgs.size() > 1000) {
            cacheMsgs.remove(0);
        }
    }

    public List<ChatMsgDTO> all() {
        return cacheMsgs;
    }

}
