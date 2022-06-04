package fun.hyman.hmchat.entity;

import lombok.Data;

/**
 * 消息体
 * 
 * @author Hyman
 * @date 2020年5月27日
 */
@Data
public class ChatMsg {

    private String clientId;
	
    private ChatMsgData data;

    @Data
    public static class ChatMsgData {

        private String fullName;

        private String userId;

        private String msg;

        /**
         * 时间，yyyy-MM-dd HH:mm:ss
         */
        private String datetime;
    }
}
