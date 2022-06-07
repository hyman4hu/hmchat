package fun.hyman.hmchat.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 消息体
 * 
 * @author Hyman
 * @date 2020年5月27日
 */
@Data
@TableName("t_chat_msg")
public class ChatMsgDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键，雪花算法
     */
    @TableId
    private Long id;

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

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
