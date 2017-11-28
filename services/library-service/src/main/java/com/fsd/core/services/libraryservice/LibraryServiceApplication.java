package com.fsd.core.services.libraryservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.util.SocketUtils;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient
//Enable the Below for Auth
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
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
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return (container -> {
            try {
                // use defaults if we can't talk to config server
                Integer minPort = env.getProperty("minPort") != null ? Integer.parseInt(env.getProperty("minPort"))
                        : 7500;
                Integer maxPort = env.getProperty("maxPort") != null ? Integer.parseInt(env.getProperty("maxPort"))
                        : 9500;
                int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                System.getProperties().put("server.port", port);
                container.setPort(port);
            } catch (Exception e) {
                log.error("Error occured while reading the min & max port properties : " + e);

            }

        });
    }
}
