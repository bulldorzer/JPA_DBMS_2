package com.korea.shop.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id // PK 설정 
    @GeneratedValue
    @Column(name="member_id") // 컬럼 이름변경
    private Long id;


    private String name;

    @Embedded // 값 타입 포함
    private Address address;

//    @OneToMany(mappedBy = "member") // 양방향 통신 거울 설정
//    private List<Order> order = new ArrayList<>();




}
