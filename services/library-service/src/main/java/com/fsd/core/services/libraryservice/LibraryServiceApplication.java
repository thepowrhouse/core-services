package com.fsd.core.services.libraryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@SpringBootApplication
@EnableDiscoveryClient
//Enable the Below for Auth
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableSwagger2
public class LibraryServiceApplication
        //extends ResourceServerConfigurerAdapter
{

    private static final Logger log = LoggerFactory.getLogger(LibraryServiceApplication.class);
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(LibraryServiceApplication.class, args);
    }

    // this is used in the clients to make calls leveraging ribbon
    @LoadBalanced
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }


    @Bean
    public Docket compositeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.fsd.core.services.libraryservice.controllers"))
                .paths(regex("/.*")).build().pathMapping("/");
    }
}
