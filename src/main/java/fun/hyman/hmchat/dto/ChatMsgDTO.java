package fun.hyman.hmchat.dto;

import lombok.Data;

/**
 * 消息体
 * 
 * @author Hyman
 * @date 2020年5月27日
 */
@Data
public class ChatMsgDTO {

    private String clientId;

    private ChatMsgData data;

    private FileInfo fileInfo;

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

    /**
     * 附件信息
     */
    @Data
    public static class FileInfo {

        /**
         * 原始文件名，上传时的文件名。不唯一
         */
        private String originFileName;

        /**
         * 程序生成的唯一ID
         */
        private String uid;

        /**
         * 文件类型，不包含"."
         */
        private String fileType;

        /**
         * 文件大小（字节）
         */
        private Long size;
    }
}
