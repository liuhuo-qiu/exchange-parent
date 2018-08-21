package cn.hpx.exchange;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;

@SpringCloudApplication
@EnableFeignClients
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ExchangePortalWebApplication extends SpringBootServletInitializer {

	/**
	 * 支持在tomcat中启动
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ExchangePortalWebApplication.class);
	}

	/**
	 * 定义fastjson为json转换工具
	 * @return
	 */
	@Bean
	public HttpMessageConverters fastjsonHttpMessageConverter(){
		//定义一个转换消息的对象
		FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();

		//添加fastjson的配置信息 比如 ：是否要格式化返回的json数据
		FastJsonConfig fastJsonConfig = new FastJsonConfig();

		fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);

		//在转换器中添加配置信息
		fastConverter.setFastJsonConfig(fastJsonConfig);

		HttpMessageConverter<?> converter = fastConverter;

		return new HttpMessageConverters(converter);

	}

	/**
	 * main方法启动
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ExchangePortalWebApplication.class, args);
	}
}
