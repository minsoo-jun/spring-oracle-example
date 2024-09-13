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
    private int category1;
    private int category2;
    private int category3;
    private int category4;
    private String lat_long;
    private String city;

}
