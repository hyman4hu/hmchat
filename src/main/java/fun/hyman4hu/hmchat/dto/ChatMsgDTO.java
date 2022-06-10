package fun.hyman4hu.hmchat.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import fun.hyman4hu.hmchat.entity.ChatMsgDO;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 消息体
 * 
 * @author Hyman
 * @date 2020年5月27日
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
public class ChatMsgDTO {

    public ChatMsgDTO(ChatMsgDO po) {
        this.fullName = po.getFullName();
        this.userId = po.getUserId();
        this.msg = po.getMsg();
        this.datetime = po.getDatetime();
        this.msgType = po.getMsgType();
        this.fileOriginName = po.getFileOriginName();
        this.fileType = po.getFileType();
        this.fileSize = po.getFileSize();
        this.filePath = po.getFilePath();
    }

    /**
     * 用户姓名
     */
    private String fullName;

    /**
     * 用户ID
     */
    private long userId;

    /**
     * 消息内容
     */
    private String msg;

    /**
     * 时间，yyyy-MM-dd HH:mm:ss
     */
    private String datetime;

    /**
     * 消息类型：1-text、2-file
     */
    private String msgType;

    /**
     * 原始文件名，上传时的文件名。不唯一
     */
    private String fileOriginName;

    /**
     * 文件类型，不包含"."
     */
    private String fileType;

    /**
     * 文件大小，字节
     */
    private Long fileSize;

    /**
     * 真实存储文件路径
     */
    private String filePath;
}
