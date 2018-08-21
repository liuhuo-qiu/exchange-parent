package cn.hpx.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ExchangeRigsterApplication extends SpringBootServletInitializer {


	/**
	 * 支持在tomcat中启动
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExchangeRigsterApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExchangeRigsterApplication.class, args);
	}
}
