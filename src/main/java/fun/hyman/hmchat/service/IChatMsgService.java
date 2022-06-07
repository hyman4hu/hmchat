package fun.hyman.hmchat.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;

import fun.hyman.hmchat.dto.ChatMsgDTO;
import fun.hyman.hmchat.entity.ChatMsgDO;

/**
 * 
 * @author huyin3
 * @date 2022/06/07
 */
public interface IChatMsgService extends IService<ChatMsgDO> {

    void save(ChatMsgDTO msg);

    List<ChatMsgDTO> all();

}
