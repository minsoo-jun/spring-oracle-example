package com.example.web.service;

import com.example.web.model.StoreInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class StoreInfoService {

    @Value("${api.store.url}")
    private String apiUrl;

    RestTemplate restTemplate;
    ObjectMapper objectMapper;
    HttpHeaders headers;

    public List<StoreInfo> getAllStoreInfo() throws IOException {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();

        String allApiUrl = apiUrl + "/all";
        System.out.println("allApiUrl -->" + allApiUrl);

        String response = restTemplate.getForObject(allApiUrl, String.class);

        StoreInfo[] storeInfoArray = objectMapper.readValue(response, StoreInfo[].class);

        return Arrays.asList(storeInfoArray);
    }

    //現在は1件取得
    public StoreInfo searchStore(String storeName) throws IOException {
        restTemplate = new RestTemplate();
        objectMapper = new ObjectMapper();

        System.out.println("storeName --->" + "/" + storeName);

        String oneStoreApiUrl = apiUrl + "/" + storeName;

        System.out.println("oneStoreApiUrl --->" + oneStoreApiUrl);
        StoreInfo storeInfo = restTemplate.getForObject(oneStoreApiUrl, StoreInfo.class);

        return storeInfo; 
    }

    /*
     * 新規登録
     */
    public void saveStoreInfo(StoreInfo storeInfo) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<StoreInfo> request = new HttpEntity<>(storeInfo, headers);

        // API Endpoint URL
        String newApiUrl = apiUrl + "/add"; 

        try {
            // POST Request/Response
            ResponseEntity<String> response = restTemplate.postForEntity(newApiUrl, request, String.class);

            // Response status 201: Ok
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("StoreInfo saved successfully.");
            } else {
                System.err.println("Failed to save StoreInfo. Status code: " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            System.err.println("Error saving StoreInfo: " + e.getMessage());
        }
    }    

    /*
     * 編集
     */
    public void updateStoreInfo(StoreInfo storeInfo) {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        HttpEntity<StoreInfo> request = new HttpEntity<>(storeInfo, headers);
    
        // API Endpoint URL
        String editApiUrl = apiUrl + "/" + storeInfo.getStore(); 
    
        try {
            ResponseEntity<String> response = restTemplate.exchange(editApiUrl, HttpMethod.PUT, request, String.class);
    
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("StoreInfo updated successfully.");
            } else {
                System.err.println("Failed to update StoreInfo. Status code: " + response.getStatusCodeValue());
            }
        } catch (Exception e) {
            System.err.println("Error updating StoreInfo: " + e.getMessage());
        }
    }

    public void deleteStore(String storeName) {
        RestTemplate restTemplate = new RestTemplate();
    
        // API  URL
        String deleteApiUrl = apiUrl + "/delete/" + storeName; 
    
        try {
            restTemplate.getForEntity(deleteApiUrl, String.class);
        } catch (Exception e) {
            System.err.println("Error deleting StoreInfo: " + e.getMessage());
        }
    }
    
}