package com.korea.shop.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name="orders") // order가 db 예약어 이기떄문에 테이블명 변경
public class Order {

    @Id // PK 설정
    @GeneratedValue
    @Column(name="order_id")            // 테이블 저장 필드명
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")     // 양방향통신 주인설정 FK
    private Member member;              // 주문자

    // LAZY(지연로딩)
    // cascade(위에서 아래로 흐르는) 부모에서 자식으로 흘러갈껀지 확인
    // 상태 변화 연동여부 (데이터 변동시 관련된 데이터도 같이 변경)
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")   // 주인권한
    private Delivery delivery;          // 배송정보

    private LocalDateTime orderDate;    // 주문일시

    @Enumerated(EnumType.STRING)
    private OrderStatus status;         // 주문상태

    // 연관관계 메서드
//    public void setMember (Member member){
//        this.member = member;
//        member.getOrder().add(this);
//    }
}
