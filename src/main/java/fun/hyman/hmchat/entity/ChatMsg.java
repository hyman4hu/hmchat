package fun.hyman.hmchat.entity;

import lombok.Data;

/**
 * @author Hyman
 * @date 2020年5月27日
 */
@Data
public class ChatMsg {
	
	private String username;
	
	private long timestamp;
	
	private String msg;
}
