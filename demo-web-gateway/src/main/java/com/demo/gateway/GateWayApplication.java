package com.demo.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.reactive.config.EnableWebFlux;

/**
 * @Author: sunYF
 * @Description:
 * @Date: Create in 11:27 2020/11/13
 */
@EnableWebFlux
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class GateWayApplication   {
		public static void main(String[] args) {
				SpringApplication.run(GateWayApplication.class, args);
		}

}
