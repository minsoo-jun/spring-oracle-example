package com.example.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.api.entity.StoreInfo;

public interface UserRepository  extends JpaRepository<StoreInfo, String> {

    @Query(value = "SELECT store, address, category1, category2, category3, category4, lat_long, city FROM store_info order by category1 desc", nativeQuery = true)
    List<StoreInfo> allStore();

    @Query(value = "SELECT store, address, category1, category2, category3, category4, lat_long, city FROM store_info where city =:city order by category1 desc", nativeQuery = true)
    List<StoreInfo> findByCity(@Param("city") String city);

    /*
     INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) VALUES (:store, :address, :category1, :category2, :category3, :category4, :lat_long, :city);
     */
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO store_info (store, address, category1, category2, category3, category4, lat_long, city) VALUES (:store, :address, :category1, :category2, :category3, :category4, :lat_long, :city)", nativeQuery = true)
    int creaStoreInfo(@Param("store") String store,@Param("address") String address, @Param(value = "category1") Integer category1, @Param(value = "category2") Integer category2,@Param(value = "category3") Integer category3, @Param(value = "category4") Integer category4, @Param(value = "lat_long") String lat_long, @Param(value = "city") String city);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM store_info WHERE store = :store", nativeQuery = true)
    void deleteStoreInfo(@Param("store") String store);    
}
