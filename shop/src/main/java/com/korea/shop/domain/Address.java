package com.korea.shop.domain;

import jakarta.persistence.Embeddable;


// 임베디드 값 타입 설계
@Embeddable
public class Address {

    // PK 필요없음
    private String city;
    private String street;
    private int zipcode;

    // 기본 생성자 생성 (필수)
    protected Address () {}

    public Address (String city, String street, int zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
