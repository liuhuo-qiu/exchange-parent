package cn.hpx.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.config.server.EnableConfigServer;


@SpringBootApplication
@EnableConfigServer
public class ExchangeConfigServerApplication extends SpringBootServletInitializer {


	/**
	 * 支持在tomcat中启动
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExchangeConfigServerApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ExchangeConfigServerApplication.class, args);
	}
}
