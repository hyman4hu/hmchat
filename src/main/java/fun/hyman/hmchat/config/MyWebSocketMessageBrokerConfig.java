package fun.hyman.hmchat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * @author Hyman
 * @date 2019年7月10日
 */
@Configuration
@EnableWebSocketMessageBroker // 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
public class MyWebSocketMessageBrokerConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp-websocket") // 添加STOMP协议的端点
				.setAllowedOrigins("*"); // 设置允许可跨域的域名
//				.withSockJS(); // 指定使用SockJS协议

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 配置消息代理，前端通过这个代理来进行消息订阅,
		// 消息代理可以有一个，也可以有多个，用 “，” 号分隔
		// 这里配置了两个，"/topic"用作全局推送，"/queue"用做点对点使用
		registry.enableSimpleBroker("/topic", "/queue");
		registry.setApplicationDestinationPrefixes("/app");// 设置客户端订阅消息的基础路径
//		registry.setPathMatcher(new AntPathMatcher("."));// 可以以“.”来分割路径，看看类级别的@messageMapping和方法级别的@messageMapping
	}

}
