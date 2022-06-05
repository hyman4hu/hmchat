package fun.hyman.hmchat.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import fun.hyman.hmchat.component.IpHandshakeInterceptor;

/**
 * @author Hyman
 * @date 2019年7月10日
 */
@Configuration
@EnableWebSocketMessageBroker // 此注解表示使用STOMP协议来传输基于消息代理的消息，此时可以在@Controller类中使用@MessageMapping
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") // 添加STOMP协议的端点
                .addInterceptors(new IpHandshakeInterceptor())
				.setAllowedOrigins("*"); // 设置允许可跨域的域名
//				.withSockJS(); // 指定使用SockJS协议

	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 配置消息代理，前端通过这个代理来进行消息订阅,
		// 消息代理可以有一个，也可以有多个，用 “，” 号分隔
		// 这里配置了两个，"/topic"用作全局推送，"/queue"用做点对点使用
        registry.enableSimpleBroker("/topic", "/user");
		registry.setApplicationDestinationPrefixes("/app");// 设置客户端订阅消息的基础路径
        // 用户名称前
        registry.setUserDestinationPrefix("/user");
//		registry.setPathMatcher(new AntPathMatcher("."));// 可以以“.”来分割路径，看看类级别的@messageMapping和方法级别的@messageMapping
	}

    @Bean
    public TaskScheduler heartBeatScheduler() {
        return new ThreadPoolTaskScheduler();
    }

}
