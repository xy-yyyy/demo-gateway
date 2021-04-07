package com.demo.gateway.security.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.util.AntPathMatcher;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 11:11 2020/11/17
 */
@Slf4j
@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

		private static final String[] AUTH_WHITELIST = new String[]
						{"/demo-basic/**","/demo-user/**","/demo-order/**"};
		@Bean
		public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
				log.info("WebFlux Security begin");
				http.cors();
				http.csrf().disable();

				return http.authorizeExchange()
								//无需进行权限过滤的请求路径
								.pathMatchers(AUTH_WHITELIST).permitAll()
								//option 请求默认放行
								.pathMatchers(HttpMethod.OPTIONS).permitAll()
								.anyExchange()
								.authenticated()
								.and()
								.build();

		}

		// 路径匹配器
		@Bean
		public AntPathMatcher antPathMatcher() {
				return new AntPathMatcher();
		}
}