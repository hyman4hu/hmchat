package fun.hyman.hmchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling // 开启定时任务
@SpringBootApplication
public class HmchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmchatApplication.class, args);
	}

}
