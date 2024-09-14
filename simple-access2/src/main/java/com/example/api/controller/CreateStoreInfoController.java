package com.example.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.domain.entity.StoreInfo;
import com.example.api.domain.service.StoreInfoService;

@RestController
@RequestMapping("/api/v1/store-info")
public class CreateStoreInfoController {
    
    @Autowired
    private StoreInfoService storeInfoService;

    //全件取得
    @GetMapping("/all")
    public List<StoreInfo> getAllStoreInfo() {
        return storeInfoService.getAllStoreInfo();
    }    

    @GetMapping("/{store}")
    public StoreInfo getStore(@PathVariable String store) {
        return storeInfoService.getStoreInfo(store);
    }    

    // StoreInfo를 Oracle DB에 Insert 하는 메소드
    /*
        curl --location 'https://api-oracle-904838507507.asia-northeast1.run.app/api/v1/store-info' \
        --header 'Content-Type: application/json' \
        --data '{ 
            "store":"test-store-003", 
            "address": "test-address", 
            "category1": 100000, 
            "category2": 22222,  
            "category3": 333333, 
            "category4": 444444, 
            "lat_long": "35.1994, 129.0596" , 
            "city": "busan" 
        }'    
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createStoreInfo(@RequestBody StoreInfo storeInfo) {
        try {
            // storeInfoService의 createStoreInfo 메소드를 호출하여 DB에 저장
            storeInfoService.createStoreInfo(storeInfo);

            // 성공적으로 저장되었을 경우 201 Created 상태 코드와 저장된 데이터를 반환
            Map<String, Object> response = new HashMap<>();
            response.put("message", "StoreInfo created successfully.");
            response.put("data", storeInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
            // Exception 발생 시 500 Internal Server Error 상태 코드와 에러 메시지를 반환
            Map<String, Object> errorResponse = new HashMap<>();
            errorResponse.put("error", Map.of("code", 500, "message", "Failed to create StoreInfo."));
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }    

    /*
        curl --location --request PUT 'https://api-oracle-904838507507.asia-northeast1.run.app/api/v1/store-info/test-store-001' \
        --header 'Content-Type: application/json' \
        --data '{
            "address": "updated-address4"
        }'
     */

     @PutMapping("/{store}")
     public ResponseEntity<Map<String, Object>> updateStoreInfo(@PathVariable String store, @RequestBody StoreInfo updatedStoreInfo) {
         try {
             // 기존 StoreInfo 데이터 조회
             StoreInfo existingStoreInfo = storeInfoService.getStoreInfo(store);
 
             if (existingStoreInfo == null) {
                 // StoreInfo 데이터가 없는 경우 404 Not Found 반환
                 Map<String, Object> errorResponse = new HashMap<>();
                 errorResponse.put("error", Map.of("code", 404, "message", "StoreInfo not found."));
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
             }
 
            // 기존 데이터 업데이트
            if (Objects.nonNull(updatedStoreInfo.getAddress())) {
                existingStoreInfo.setAddress(updatedStoreInfo.getAddress());
            }

            if (Objects.nonNull(updatedStoreInfo.getCategory1())) {
                existingStoreInfo.setCategory1(updatedStoreInfo.getCategory1());
            }
            if (Objects.nonNull(updatedStoreInfo.getCategory2())) {
                existingStoreInfo.setCategory2(updatedStoreInfo.getCategory2());
            }
            if (Objects.nonNull(updatedStoreInfo.getCategory3())) {
                existingStoreInfo.setCategory3(updatedStoreInfo.getCategory3());
            }
            if (Objects.nonNull(updatedStoreInfo.getCategory4())) {
                existingStoreInfo.setCategory4(updatedStoreInfo.getCategory4());
            }
            if (Objects.nonNull(updatedStoreInfo.getLat_long())) {
                existingStoreInfo.setLat_long(updatedStoreInfo.getLat_long());
            }
            if (Objects.nonNull(updatedStoreInfo.getCity())) {
                existingStoreInfo.setCity(updatedStoreInfo.getCity());
            }

             // 업데이트된 StoreInfo 저장
             storeInfoService.createStoreInfo(existingStoreInfo);
 
             // 성공적으로 업데이트되었을 경우 200 OK 상태 코드와 업데이트된 데이터 반환
             Map<String, Object> response = new HashMap<>();
             response.put("message", "StoreInfo updated successfully.");
             response.put("data", existingStoreInfo);
             return ResponseEntity.ok(response);
 
         } catch (Exception e) {
             // Exception 발생 시 500 Internal Server Error 상태 코드와 에러 메시지 반환
             Map<String, Object> errorResponse = new HashMap<>();
             errorResponse.put("error", Map.of("code", 500, "message", "Failed to update StoreInfo."));
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
         }
     }        
}
