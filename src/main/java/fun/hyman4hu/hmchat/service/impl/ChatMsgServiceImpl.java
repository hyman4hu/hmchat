package fun.hyman4hu.hmchat.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import cn.hutool.core.collection.CollectionUtil;
import fun.hyman4hu.hmchat.dto.ChatMsgDTO;
import fun.hyman4hu.hmchat.entity.ChatMsgDO;
import fun.hyman4hu.hmchat.mapper.ChatMsgMapper;
import fun.hyman4hu.hmchat.service.IChatMsgService;

/**
 * 
 * @author Hyman
 * @date 2022/06/07
 */
@Service
public class ChatMsgServiceImpl extends ServiceImpl<ChatMsgMapper, ChatMsgDO> implements IChatMsgService {

    @Override
    public void save(ChatMsgDTO msg) {
        ChatMsgDO po = new ChatMsgDO();
        LocalDateTime now = LocalDateTime.now();
        po.setFullName(msg.getFullName());
        po.setUserId(msg.getUserId());
        po.setMsg(msg.getMsg());
        po.setDatetime(msg.getDatetime());
        po.setMsgType(msg.getMsgType());
        po.setFileOriginName(msg.getFileOriginName());
        po.setFileType(msg.getFileType());
        po.setFileSize(msg.getFileSize());
        po.setFilePath(msg.getFilePath());
        po.setCreateTime(now);
        po.setUpdateTime(now);
        this.baseMapper.insert(po);
    }

    @Override
    public List<ChatMsgDTO> all() {
        LambdaQueryWrapper<ChatMsgDO> qw = Wrappers.lambdaQuery();
        qw.last("LIMIT 1000");
        qw.orderByDesc(ChatMsgDO::getId);
        List<ChatMsgDO> pos = this.list(qw);
        List<ChatMsgDTO> dtos = new ArrayList<>();
        if (CollectionUtil.isEmpty(pos)) {
            return dtos;
        }
        for (int i = pos.size() - 1; i >= 0; i--) {
            ChatMsgDO po = pos.get(i);
            ChatMsgDTO dto = new ChatMsgDTO(po);
            dtos.add(dto);
        }
        return dtos;
    }

}
