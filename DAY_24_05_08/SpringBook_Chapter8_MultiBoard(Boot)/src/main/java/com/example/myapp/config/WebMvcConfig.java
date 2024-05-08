package com.example.myapp.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.example.myapp.common.filter.LoginInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	@Bean // 빈태그 대신 어노테이션 사용
	LocaleResolver localeResolver() {
		SessionLocaleResolver slr = new SessionLocaleResolver();
		slr.setDefaultLocale(Locale.KOREAN);
		return slr;
	}

	@Bean
	MessageSource messageSource() { // 국제화
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("i18n/message"); // 국제화 설정파일 이름 : i18n/message_(두자리 언어코드).properties -> i18n/message_ko.properties
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	LocaleChangeInterceptor localeChangeInterceptor() { // 스프링에서 제공해주는 인터셉터 : locale 변경 시 인터셉터됨 => 지역에 따라 (언어, 시간, 통화기호, 구분자(세자리마다 구분자, 소주점자리 표현))이 변경됨
		LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
		lci.setParamName("lang");
		return lci;
	}

	@Bean
	LoginInterceptor loginInterceptor() {
		/*
		 * <bean id="loginInterceptor"
		 * 	class="com.example.myapp
		 * */
		return new LoginInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
		registry.addInterceptor(loginInterceptor())
				.addPathPatterns("/file/**")
				.addPathPatterns("/board/write/**")
				.addPathPatterns("/board/update/**")
				.addPathPatterns("/board/reply/**")
				.addPathPatterns("/board/delete/**");
	}

}
