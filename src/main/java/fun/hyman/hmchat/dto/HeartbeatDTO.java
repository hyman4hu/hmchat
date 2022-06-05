package fun.hyman.hmchat.dto;

import lombok.Data;

/**
 * 心跳消息
 * 
 * @author Hyman
 * @date 2022/06/05
 */
@Data
public class HeartbeatDTO {

    private String clientId;

    private String clientName;

}
