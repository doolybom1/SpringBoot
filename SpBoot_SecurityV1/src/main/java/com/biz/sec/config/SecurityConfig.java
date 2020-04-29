package com.biz.sec.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.thymeleaf.extras.springsecurity5.dialect.SpringSecurityDialect;

import com.biz.sec.service.SecurityUserServiceImpl;

import lombok.RequiredArgsConstructor;


/*
 * spring security의 customizing 첫번째 단계
 * security-context.xml에 설정했던 옵션들을 지정해주는 클래스
 */
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final SecurityUserServiceImpl sUserService;
	

	@Override
	public void configure(WebSecurity web) throws Exception {
		//super.configure(web);
		
		// security를 검사하지 않을 요청
		web.ignoring().antMatchers("/static/css/**", "/static/js/**")
					.antMatchers("/static/images/**")
					.antMatchers("static/music/**");
	}
	
	
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// TODO Auto-generated method stub
		// super.configure(http);
		httpSecurity.authorizeRequests()
			.antMatchers("/hello").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user/login").permitAll()
			.antMatchers("/**").permitAll();
		
		httpSecurity.authorizeRequests()
			.and().formLogin()
				.loginPage("/user/login")
				.failureUrl("/user/login?error")
				.usernameParameter("username")
				.passwordParameter("password")
			.and().logout()
				.logoutSuccessUrl("/");
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.userDetailsService(sUserService).passwordEncoder(sUserService.getPasswordEncoder());
	}



	
	/*
	 * html templates 파일에서 sec: tag를 사용할 때 값, 설정, 함수 등을 사용할 수 있도록 객체를 전달하는 용도
	 */
	@Bean
	public SpringSecurityDialect springSecurityDialect() {
		return new SpringSecurityDialect();
	}



}