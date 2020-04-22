package com.biz.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * Spring Boot에서 가장 기본이 되는 클래스이며 프로젝트에 대한 설정이 시작되는 부분이다
 * 이 클래스에는 @SpringBootApplication 어노테이션이 설정되어야 하고 이 어노테이션이 설정된
 * 클래스가 있는 패키지가 base 패키지가 된다
 * 
 * @author dooly
 *
 */

// @SpringBootApplication이 아래 3개를 다 포함하고 있음
//		@EnableAutoConfiguration
//		@ComponentScan
//		@SpringBootConfiguration

@SpringBootApplication
public class SpBootHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpBootHelloApplication.class, args);
	}
}
