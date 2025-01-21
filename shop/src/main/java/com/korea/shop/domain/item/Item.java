package com.korea.shop.domain.item;

import com.korea.shop.domain.CategoryItem;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = "categoryItems") // toString 메서드에서 제외시킬 양방향참조가 일어나는 객체
// 상속관계에서 전략을 정해줌
// 싱글 테이블로 합칠껀지 관계테이블로 관리할껀지 설정
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "dtype") // 구분자를 지정할 필드 이름 부모쪽에서 씀
public class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;        // 상품명
    private int price;          // 가격
    private int stockQuantity;  //재고


    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();
}
