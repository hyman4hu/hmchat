package fun.hyman4hu.hmchat.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import fun.hyman4hu.hmchat.entity.SysUserDO;
import fun.hyman4hu.hmchat.mapper.SysUserMapper;
import fun.hyman4hu.hmchat.service.ISysUserService;

/**
 * 用户
 * 
 * @author Hyman
 * @date 2022/06/10
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserDO> implements ISysUserService {

}
