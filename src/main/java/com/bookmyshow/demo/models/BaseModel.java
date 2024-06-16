package com.bookmyshow.demo.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass

public class BaseModel {

    @Id // This annotation is used to define the primary key
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY) // This annotation is used to define the primary key generation strategy
    private Long id;
    private Date createdAt;
    private Date updatedAt;



}
