package com.stressbucket.stressbucketapi;

import com.stressbucket.stressbucketapi.filter.AuthFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StressBucketApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StressBucketApiApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<AuthFilter> filterFilterRegistrationBean(){
		FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
		AuthFilter authFilter = new AuthFilter();
		registrationBean.setFilter(authFilter);
		// Intercepts below URL patterns with token authentication filter. Does not affect login/registration
		registrationBean.addUrlPatterns("/api/buckets/*");
		return registrationBean;
	}

}
