package fun.hyman.hmchat.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 系统附件信息 实体类
 * 
 * @author huyin3
 * @date 2022/05/18
 */
@Data
@TableName("sys_attachment")
public class SysAttachmentDO {

    @TableId
    private Long id;

    /**
     * 关联数据ID
     */
    private Long refId;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件大小
     */
    private Long size;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 数据子类型
     */
    private String dataSubType;

    /**
     * 顺序
     */
    private Integer seq;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
