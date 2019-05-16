package cn.lanaya.business;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BusinessInterfaceApp {

	public static void main(String[] args) {
		SpringApplication.run(BusinessInterfaceApp.class, args);
	}

}
