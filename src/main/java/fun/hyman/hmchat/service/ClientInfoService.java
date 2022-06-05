package fun.hyman.hmchat.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import cn.hutool.cache.Cache;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.date.LocalDateTimeUtil;
import fun.hyman.hmchat.dto.HeartbeatDTO;
import fun.hyman.hmchat.vo.ClientInfoVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author huyin3
 * @date 2022/06/04
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ClientInfoService {

    private Cache<String, ClientInfoVO> clientInfoCache = new TimedCache<>(20 * 1000);// 保存客户端信息

    private final SimpMessagingTemplate messagingTemplate;

    @Scheduled(cron = "*/10 * * * * *") // 每10秒执行一次
    public void scheduledMethod() {
        log.info("scheduledMethod - {}", LocalDateTimeUtil.formatNormal(LocalDateTime.now()));
        messagingTemplate.convertAndSend("/topic/clientInfos", clientInfos());
    }

    public List<ClientInfoVO> clientInfos() {
        List<ClientInfoVO> vos = new ArrayList<>();
        clientInfoCache.forEach(vo -> {
            vos.add(vo);
        });
        return vos;
    }

    public Set<String> getClientIds() {
        Set<String> ret = new HashSet<>();
        clientInfoCache.forEach(vo -> {
            ret.add(vo.getClientId());
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
        ClientInfoVO vo = new ClientInfoVO();
        vo.setClientId(heartbeat.getClientId());
        vo.setClientName(heartbeat.getClientName());
        vo.setIp(ip);
        clientInfoCache.put(heartbeat.getClientId(), vo);
    }

}
