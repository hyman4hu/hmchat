package fun.hyman4hu.hmchat.param;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 
 * @author Hyman
 * @date 2022/06/10
 */
@Data
public class LoginParam {

    @NotEmpty(message = "username 不能为空")
    private String username;

}
