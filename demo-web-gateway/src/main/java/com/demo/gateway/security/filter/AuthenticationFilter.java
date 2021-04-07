package com.demo.gateway.security.filter;

import com.demo.gateway.security.utils.ReactiveRequestContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Author: sunYF
 * @Description: 网关统全局过滤器
 * @Date: Create in 15:33 2020/11/17
 */
@Configuration
@Component
@Slf4j
public class AuthenticationFilter implements GlobalFilter, Ordered {


		@Override
		public Mono<Void> filter(ServerWebExchange serverWebExchange, GatewayFilterChain webFilterChain) {
				ServerHttpRequest request = serverWebExchange.getRequest();
				/*	List<String> tokenObjectrequest=request.getHeaders().get("token");*/
				ServerHttpResponse response = serverWebExchange.getResponse();
				response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");

				String requestUrl = serverWebExchange.getRequest().getPath().value();
				AntPathMatcher pathMatcher = new AntPathMatcher();
				if (requestUrl.contains("login")) {
						return webFilterChain.filter(serverWebExchange);
				}
				//demo-basic所有服务放行
				if (pathMatcher.match("/demo-basic/**", requestUrl)) {
						return webFilterChain.filter(serverWebExchange);
				}
				//demo-user所有服务放行
				if (pathMatcher.match("/demo-user/**", requestUrl)) {
						return webFilterChain.filter(serverWebExchange);
				}
				//demo-order所有服务放行
				if (pathMatcher.match("/demo-order/**", requestUrl)) {
						return webFilterChain.filter(serverWebExchange);
				}
				return webFilterChain.filter(serverWebExchange)
								.subscriberContext(ctx -> ctx.put(ReactiveRequestContextHolder.CONTEXT_KEY, request));
		}
		@Override
		public int getOrder() {
				return 0;
		}


}
