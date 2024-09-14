package com.example.api.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "store_info")
@Getter
@Setter
public class StoreInfo {
    @Id
    private String store;
    private String address;
    private Integer category1;
    private Integer category2;
    private Integer category3;
    private Integer category4;
    private String lat_long;
    private String city;

}
