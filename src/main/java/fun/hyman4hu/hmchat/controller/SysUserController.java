package fun.hyman4hu.hmchat.controller;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

import fun.hyman4hu.hmchat.common.R;
import fun.hyman4hu.hmchat.entity.SysUserDO;
import fun.hyman4hu.hmchat.param.LoginParam;
import fun.hyman4hu.hmchat.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统用户
 * 
 * @author Hyman
 * @date 2022/06/10
 */
@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class SysUserController {

    private final ISysUserService sysUserService;

    /**
     * 登录
     */
    @RequestMapping("/login")
    public R<?> login(@RequestParam @Valid LoginParam param) {
        log.info("{} 尝试登录", param.getUsername());
        LambdaQueryWrapper<SysUserDO> qw = Wrappers.lambdaQuery();
        qw.eq(SysUserDO::getUsername, param.getUsername());
        qw.last("LIMIT 1");
        SysUserDO po = this.sysUserService.getOne(qw);
        if (po != null) {
            po.setLastLoginTime(LocalDateTime.now());
            this.sysUserService.updateById(po);
            return R.ok(po);
        } else {
            return R.failed("用户名或密码错误");
        }
    }
}
