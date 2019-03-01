package com.nexaas;

import com.nexaas.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class DesafioProgramacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioProgramacaoApplication.class, args);
	}

}
