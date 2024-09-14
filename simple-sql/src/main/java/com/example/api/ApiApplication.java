package com.example.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.HashMap;
import java.util.Map;

@SpringBootApplication

public class ApiApplication {

       
    //アクセステスト
	@GetMapping("/")
	public Map<String, Object> hello(){
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Hello from Spring Boot API!");
        data.put("timestamp", System.currentTimeMillis());
        return data;
	}
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
