package com.wistronits.aml;

import com.wistronits.aml.filter.CustomFilter;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Controller;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.wistronits.aml.mapper")
@Controller
@EnableScheduling
@EnableCaching
public class AmlApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmlApplication.class, args);
		System.out.println("                                     _ooOoo_");
		System.out.println("                                    o8888888o");
		System.out.println("                                    88\" . \"88");
		System.out.println("                                    (| -_- |)");
		System.out.println("                                    O\\  =  /O");
		System.out.println("                                 ____/`---'\\____");
		System.out.println("                               .'  \\\\|     |//  `.");
		System.out.println("                              /  \\\\|||  :  |||//  \\");
		System.out.println("                             /  _||||| -:- |||||-  \\");
		System.out.println("                             |   | \\\\\\  -  /// |   |");
		System.out.println("                             | \\_|  ''\\---/''  |   |");
		System.out.println("                             \\  .-\\__  `-`  ___/-. /");
		System.out.println("                           ___`. .'  /--.--\\  `. . __ ");
		System.out.println("                        .\"\" '<  `.___\\_<|>_/___.'  >'\"\".");
		System.out.println("                       | | :  `- \\`.;`\\ _ /`;.`/ - ` : | |");
		System.out.println("                       \\  \\ `-.   \\_ __\\ /__ _/   .-` /  /");
		System.out.println("                  ======`-.____`-.___\\_____/___.-`____.-'======");
		System.out.println("                                     `=---='");
		System.out.println("                  ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
		System.out.println("                             佛祖保佑        永无BUG");
	}

	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		return corsConfiguration;
	}

	/**
	 * 跨域过滤器
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());
		return new CorsFilter(source);
	}


	/**
	 * 注册过滤器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {

		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		CustomFilter customFilter = new CustomFilter();
		registrationBean.setFilter(customFilter);
		List<String> urlPatterns = new ArrayList<String>();
		urlPatterns.add("/token/*");
		registrationBean.setUrlPatterns(urlPatterns);
		return registrationBean;
	}

	/**
	 * 创建一个bean
	 * @return
	 */
	@Bean(name = "sessionFilter")
	public Filter sessionFilter() {
		return new CustomFilter();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
	{
		RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		RedisSerializer<String> stringSerializer = new StringRedisSerializer();
		template.setConnectionFactory(redisConnectionFactory);
		template.setKeySerializer(stringSerializer );
		template.setValueSerializer(stringSerializer );
		template.setHashKeySerializer(stringSerializer );
		template.setHashValueSerializer(stringSerializer );
		return template;
	}
}
