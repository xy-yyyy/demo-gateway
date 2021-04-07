package com.demo.gateway.security.utils;

import reactor.core.publisher.Mono;
import org.springframework.http.server.reactive.ServerHttpRequest;
/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 21:06 2020/11/17
 */
public class ReactiveRequestContextHolder {
		public static final  Class<ServerHttpRequest> CONTEXT_KEY = ServerHttpRequest.class;

		public static Mono<ServerHttpRequest> getRequest() {
				return Mono.subscriberContext()
								.map(ctx -> ctx.get(CONTEXT_KEY));
		}

}
