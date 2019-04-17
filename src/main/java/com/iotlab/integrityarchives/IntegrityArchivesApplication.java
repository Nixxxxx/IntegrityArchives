package com.iotlab.integrityarchives;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * 若打包成war包,则需要继承 org.springframework.boot.context.web.SpringBootServletInitializer类,覆盖其config(SpringApplicationBuilder)方法
 */
@SpringBootApplication
@MapperScan("com.iotlab.integrityarchives.dao")
public class IntegrityArchivesApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(IntegrityArchivesApplication.class);
	}


	public static void main(String[] args) {
		SpringApplication.run(IntegrityArchivesApplication.class, args);
	}

}
