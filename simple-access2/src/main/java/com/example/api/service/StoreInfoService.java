package com.example.api.service;

import com.example.api.repository.StoreInfoRepository;
import com.example.api.entity.StoreInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreInfoService {

    @Autowired
    private StoreInfoRepository storeInfoRepository;

    public List<StoreInfo> getAllStoreInfo() {
        return storeInfoRepository.findAll();
    }    
    
    public StoreInfo getStoreInfo(String store) {
        return storeInfoRepository.findByStore(store);
    }

    public void createStoreInfo(StoreInfo storeInfo) {
        storeInfoRepository.save(storeInfo);
    }

    public void deleteStoreInfo(String store) {
        storeInfoRepository.deleteById(store);
    }     

}
