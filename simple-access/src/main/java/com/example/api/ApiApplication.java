package com.example.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.entity.StoreInfo;
import com.example.api.domain.service.StoreInfoService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@RestController
public class ApiApplication {

    @Autowired
    private StoreInfoService storeInfoService;

    //全件取得
    @GetMapping("/api/v1/store-info")
    public List<StoreInfo> getAllStoreInfo() {
        return storeInfoService.getAllStoreInfo();
    }    

    @GetMapping("/api/v1/store-info/{store}")
    public StoreInfo getStore(@PathVariable String store) {
        return storeInfoService.getStoreInfo(store);
    }
    //アクセステスト
	@GetMapping("/")
	public Map<String, Object> hello(){
        Map<String, Object> data = new HashMap<>();
        data.put("message", "Hello from Spring Boot API!");
        data.put("timestamp", System.currentTimeMillis());
        return data;
	}


    
    //パラメータ確認
    @GetMapping("/api/v1/users/{id}")
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable Long id) {

        if (id == null) {
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", Map.of("code", 404, "message", "User not found"));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }

        Map<String, Object> successResponse = new HashMap<>();
        successResponse.put("data", id);
        return ResponseEntity.ok(successResponse);
    }	

	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}

}
