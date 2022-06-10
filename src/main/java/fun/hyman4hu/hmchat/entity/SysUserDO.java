package fun.hyman4hu.hmchat.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

/**
 * 系统用户
 * 
 * @author Hyman
 * @date 2022/06/10
 */
@Data
@TableName("sys_user")
public class SysUserDO {

    @TableId
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 姓名（全名）
     */
    private String fullName;

    /**
     * 头像文件路径
     */
    private String avatarPath;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
}
