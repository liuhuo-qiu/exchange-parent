package cn.hpx.exchange;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringCloudApplication
@MapperScan("cn.hpx.exchange.service.mapper")
@EnableTransactionManagement
public class ExchangeServiceUserApplication  extends SpringBootServletInitializer {



	/**
	 * 支持在tomcat中启动
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExchangeServiceUserApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(ExchangeServiceUserApplication.class, args);
	}
}
