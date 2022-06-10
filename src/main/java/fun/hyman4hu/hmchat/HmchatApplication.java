package fun.hyman4hu.hmchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableAsync
@EnableTransactionManagement
@EnableScheduling // 开启定时任务
@SpringBootApplication
public class HmchatApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmchatApplication.class, args);
	}

}
