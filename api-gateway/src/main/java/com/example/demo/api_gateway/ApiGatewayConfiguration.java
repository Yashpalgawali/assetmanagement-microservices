package com.example.demo.api_gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {

	@Bean
	RouteLocator gatewayRouter(RouteLocatorBuilder builder) {

		return builder.routes()
						.route(p -> p
							.path("/get")
							.filters(f -> f.addRequestHeader("MyHeader", "MYURI"))
							.uri("http://httpbin.org:80") )
						.route(p->
							p.path("/company-service/**")
							.filters(f -> f.stripPrefix(1))
							.uri("lb://company-service")
					    )
						.route(p->
							p.path("/department-service/**")
							.filters(f -> f.stripPrefix(1))
							.uri("lb://department-service")
					    )
						.route(p -> 
							p.path("/asset-type-service/**")
							.filters(f -> f.stripPrefix(1))
							.uri("lb://asset-type-service")
						)
						.route(p -> 
							p.path("/asset-service/**")
							.filters(f -> f.stripPrefix(1))
							.uri("lb://asset-service")
						)
						.route(p -> 
							p.path("/designation-service/**")
							.filters(f -> f.stripPrefix(1))
							.uri("lb://designation-service")
					    )
				.build();
	}
}
