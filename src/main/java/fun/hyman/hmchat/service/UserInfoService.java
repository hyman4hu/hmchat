package fun.hyman.hmchat.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import fun.hyman.hmchat.dto.HeartbeatDTO;
import fun.hyman.hmchat.vo.UserInfoVO;
import lombok.RequiredArgsConstructor;

/**
 * 
 * @author Hyman
 * @date 2022/06/04
 */
@Component
@RequiredArgsConstructor
public class UserInfoService {

    private Cache<String, UserInfoVO> userInfoCache = new TimedCache<>(2 * 60 * 1000);// 保存客户端信息

    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "*/10 * * * * *") // 每10秒执行一次
    public void scheduledMethod() {
        messagingTemplate.convertAndSend("/topic/userInfos", userInfos());
    }

    public List<UserInfoVO> userInfos() {
        List<UserInfoVO> vos = new ArrayList<>();
        userInfoCache.forEach(vo -> {
            vos.add(vo);
        });
        return vos;
    }

    public Set<String> getUserIds() {
        Set<String> ret = new HashSet<>();
        userInfoCache.forEach(vo -> {
            ret.add(vo.getUserId());
        });
        return ret;
    }

    /**
     * 缓存客户端信息
     * 
     * @param heartbeat 心跳信息
     * @param ip
     */
    public void cache(HeartbeatDTO heartbeat, String ip) {
        UserInfoVO vo = new UserInfoVO();
        vo.setUserId(heartbeat.getUserId());
        vo.setFullName(heartbeat.getFullName());
        vo.setIp(ip);
        userInfoCache.put(heartbeat.getUserId(), vo);
    }

}
