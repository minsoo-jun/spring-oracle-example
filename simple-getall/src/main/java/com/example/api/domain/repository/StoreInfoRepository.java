package com.example.api.domain.repository;

import com.example.api.domain.entity.StoreInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfo, String> {
    
}
