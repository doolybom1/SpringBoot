package com.biz.hello.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	
	@RequestMapping(value = "",method=RequestMethod.GET)
	public String hello() {
		return "hello korea";
	}

	@RequestMapping(value = "/hello",method=RequestMethod.GET)
	public String hello(String name) {
		return "hello korea";
	}
}
