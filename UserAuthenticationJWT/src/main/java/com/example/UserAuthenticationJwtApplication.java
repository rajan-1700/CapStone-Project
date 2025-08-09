package com.example;

import io.jsonwebtoken.security.Keys;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.nio.charset.StandardCharsets;
import java.security.Key;

@SpringBootApplication
public class UserAuthenticationJwtApplication {
	@Value("${jwt.secret}")
	private String secretKey;
	public static void main(String[] args) {
		SpringApplication.run(UserAuthenticationJwtApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<Filter> loggingFilter(Key jwtKey) {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new UserFilter(jwtKey)); // ✅ uses bean from JwtConfig
		registrationBean.addUrlPatterns("/api/v1/user/*");
		return registrationBean;
	}

}
