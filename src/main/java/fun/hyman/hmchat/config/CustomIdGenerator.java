package fun.hyman.hmchat.config;

import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;

import cn.hutool.core.util.IdUtil;

/**
 * Mybatis-Plus ID生成器，雪花算法
 * 
 * @author Hyman
 * @date 2022/04/02
 */
@Component
public class CustomIdGenerator implements IdentifierGenerator {

    public String nextIdStr() {
        return String.valueOf(IdUtil.getSnowflakeNextId());
    }

    @Override
    public Number nextId(Object entity) {
        return IdUtil.getSnowflakeNextId();
    }

}
