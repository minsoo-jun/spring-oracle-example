package com.example.api.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.entity.StoreInfo;
import com.example.api.service.StoreInfoService;

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
    
    @GetMapping("/city/{city}")
    public List<StoreInfo> findByCity(@PathVariable String city) {
        return storeInfoService.findByCity(city);
    }

    /*
        curl --location 'https://api-oracle-904838507507.asia-northeast1.run.app/api/v1/store-info/add' \
        --header 'Content-Type: application/json' \
        --data '{ 
            "store":"test-store-099", 
            "address": "test-address", 
            "category1": 100000, 
            "category2": 22222,  
            "category3": 333333, 
            "category4": 444444, 
            "lat_long": "35.1994, 129.0596" , 
            "city": "busan" 
        }'    
     */
    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> createStoreInfo(@RequestBody StoreInfo storeInfo) {
        try {
            // storeInfoService의 createStoreInfo 
            storeInfoService.createStoreInfo(storeInfo);

            // 
            Map<String, Object> response = new HashMap<>();
            response.put("message", "StoreInfo created successfully.");
            response.put("data", storeInfo);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (Exception e) {
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
             StoreInfo existingStoreInfo = storeInfoService.getStoreInfo(store);
 
             if (existingStoreInfo == null) {
                 Map<String, Object> errorResponse = new HashMap<>();
                 errorResponse.put("error", Map.of("code", 404, "message", "StoreInfo not found."));
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
             }
 
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

             // 
             storeInfoService.createStoreInfo(existingStoreInfo);
 
             // 
             Map<String, Object> response = new HashMap<>();
             response.put("message", "StoreInfo updated successfully.");
             response.put("data", existingStoreInfo);
             return ResponseEntity.ok(response);
 
         } catch (Exception e) {
             // 
             Map<String, Object> errorResponse = new HashMap<>();
             errorResponse.put("error", Map.of("code", 500, "message", "Failed to update StoreInfo."));
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
         }
     }     
     
     //@DeleteMapping("/delete/{store}")
     @GetMapping("/delete/{store}")
     public ResponseEntity<Map<String, Object>> deleteStoreInfo(@PathVariable String store) {
         try {
             StoreInfo existingStoreInfo = storeInfoService.getStoreInfo(store);
 
             if (existingStoreInfo == null) {
                 Map<String, Object> errorResponse = new HashMap<>();
                 errorResponse.put("error", Map.of("code", 404, "message", "StoreInfo not found."));
                 return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
             }
 
             storeInfoService.deleteStoreInfo(store);
 
             Map<String, Object> response = new HashMap<>();
             response.put("message", "StoreInfo deleted successfully.");
             return ResponseEntity.ok(response);
 
         } catch (Exception e) {
             Map<String, Object> errorResponse = new HashMap<>();
             errorResponse.put("error", Map.of("code", 500, "message", "Failed to delete StoreInfo."));
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
         }
     }     
}
