package fun.hyman4hu.hmchat.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import fun.hyman4hu.hmchat.dto.ChatMsgDTO;
import fun.hyman4hu.hmchat.entity.ChatMsgDO;

/**
 * 聊天消息
 * 
 * @author huyin3
 * @date 2022/06/07
 */
public interface IChatMsgService extends IService<ChatMsgDO> {

    void save(ChatMsgDTO msg);

    List<ChatMsgDTO> all();

}
