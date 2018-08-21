package cn.hpx.exchange;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.sleuth.zipkin.stream.EnableZipkinStreamServer;
import zipkin.server.EnableZipkinServer;


@SpringBootApplication
@EnableZipkinStreamServer
@EnableHystrixDashboard
public class ZipKinApplication extends SpringBootServletInitializer {


	/**
	 * 支持在tomcat中启动
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ZipKinApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(ZipKinApplication.class, args);
	}
}
