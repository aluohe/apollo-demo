package com.example.demo.compent.config;

import com.example.demo.compent.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
public class WebConfig {
	   @Bean
	   public FilterRegistrationBean<MyFilter> abcFilter() {
		   FilterRegistrationBean<MyFilter> filterRegBean = new FilterRegistrationBean<>();
		   filterRegBean.setFilter(new MyFilter());
		   filterRegBean.addUrlPatterns("/notify/*");
		   filterRegBean.setOrder(Ordered.LOWEST_PRECEDENCE -1);
		   return filterRegBean;
	   }
}