package com.junitdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(JunitApplication.class, args);
	}

	public int add(int a, int b) {return a + b;}

	public Object checkNull(Object obj) {
		if(obj != null){
			return obj;
		}
		return null;
	}

	public int divide(int a, int b) {
		if(b == 0){
			throw new ArithmeticException("Divide by zero");
		}
		return a / b;
	}
}
