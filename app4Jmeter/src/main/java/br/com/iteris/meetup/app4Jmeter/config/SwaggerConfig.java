package br.com.iteris.meetup.app4Jmeter.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Header;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	

	private List<ResponseMessage> defaultReturns = new ArrayList<ResponseMessage>();;

	@Bean
	public Docket api() {
		ResponseMessage response500 = new ResponseMessage(500, "Internal server error", null,
				new HashMap<String, Header>(), new ArrayList<>());
		ResponseMessage response400 = new ResponseMessage(400, "Bad request", null, new HashMap<String, Header>(),
				new ArrayList<>());
		defaultReturns.add(response500);
		defaultReturns.add(response400);

		return new Docket(DocumentationType.SWAGGER_2).useDefaultResponseMessages(false)				
				.globalResponseMessage(RequestMethod.GET, defaultReturns)
				.globalResponseMessage(RequestMethod.POST, defaultReturns)
				.globalResponseMessage(RequestMethod.DELETE, defaultReturns)
				.globalResponseMessage(RequestMethod.PUT, defaultReturns).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class)) //				
				.paths(PathSelectors.any())				
				.build() //
				.apiInfo(metaData()) //
				.securityContexts(Lists.newArrayList(securityContext())) //
				.securitySchemes(Lists.newArrayList(apiKey()));
	}

	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("App for Jmeter API")
				.description("API de exemplo para teste Jmeter").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("Authorization", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.any()).build();
	}

	List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("Authorization", authorizationScopes));
	}
}
