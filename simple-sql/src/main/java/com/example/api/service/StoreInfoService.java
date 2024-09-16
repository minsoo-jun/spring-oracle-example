package com.example.api.service;

import com.example.api.repository.StoreInfoRepository;
import com.example.api.repository.UserRepository;
import com.example.api.entity.StoreInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreInfoService {

    @Autowired
    private StoreInfoRepository storeInfoRepository;

    @Autowired
    private UserRepository userRepository;

    public List<StoreInfo> getAllStoreInfo() {
        return userRepository.allStore();
        //return storeInfoRepository.findAll();
    } 
    
    public List<StoreInfo> findByCity(String city) {
        return userRepository.findByCity(city);
    }
    
    public StoreInfo getStoreInfo(String store) {
        return storeInfoRepository.findByStore(store);
    }

    public void createStoreInfo(StoreInfo storeInfo) {
        userRepository.creaStoreInfo(storeInfo.getStore(), storeInfo.getAddress(), storeInfo.getCategory1(), storeInfo.getCategory2(), storeInfo.getCategory3(), storeInfo.getCategory4(), storeInfo.getLat_long(), storeInfo.getCity());
        //storeInfoRepository.save(storeInfo);
    }

    public void deleteStoreInfo(String store) {
        userRepository.deleteStoreInfo(store);
    }

}
