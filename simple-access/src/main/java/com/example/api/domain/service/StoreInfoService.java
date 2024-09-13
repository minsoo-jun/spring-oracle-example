package com.example.api.domain.service;

import com.example.api.domain.repository.StoreInfoRepository;
import com.example.api.domain.entity.StoreInfo;

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

}
