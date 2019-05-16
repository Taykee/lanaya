package cn.lanaya.operator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OperatorWebApp {

	public static void main(String[] args) {
		SpringApplication.run(OperatorWebApp.class, args);
	}

}
