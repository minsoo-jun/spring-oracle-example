package com.example.api.domain.repository;

import com.example.api.domain.entity.StoreInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfo, String> {

    List<StoreInfo> findByCity(String city);
  
    StoreInfo findByStore(String store);
}



