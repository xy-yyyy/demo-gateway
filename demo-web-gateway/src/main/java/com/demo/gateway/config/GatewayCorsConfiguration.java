package com.demo.gateway.config;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 12:36 2020/11/18
 */
@Component("corsFilter")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class GatewayCorsConfiguration  implements WebFilter {
		@Override
		public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
				ServerHttpRequest request = exchange.getRequest();
				if (CorsUtils.isCorsRequest(request)) {
						HttpHeaders requestHeaders = request.getHeaders();
						ServerHttpResponse response = exchange.getResponse();
						HttpMethod requestMethod = requestHeaders.getAccessControlRequestMethod();
						HttpHeaders headers = response.getHeaders();
						headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, requestHeaders.getOrigin());
						headers.addAll(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, requestHeaders
										.getAccessControlRequestHeaders());
						if(requestMethod != null){
								headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, requestMethod.name());
						}
						headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
						headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
						headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "18000");
						if (request.getMethod() == HttpMethod.OPTIONS) {
								response.setStatusCode(HttpStatus.OK);
								return Mono.empty();
						}
				}
				return chain.filter(exchange);
		}
}
