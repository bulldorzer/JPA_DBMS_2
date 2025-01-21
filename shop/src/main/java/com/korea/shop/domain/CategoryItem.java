package com.korea.shop.domain;

import com.korea.shop.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class CategoryItem {

    @Id @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "item_id")
    private Item item;


    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "category_id")
    private  Category category;


    // 연관 관계 메서드 설정
    // 보통 주인 권한에 만들고 
    // 주인 쪽에서 데이터를 수정함
//    private void setCategory(Category category){
//        this.category = category;
//        category.getCategoryItems().add(this);
//    }
    private void addCategory(Category category){
        this.category = category;
        category.getCategoryItems().add(this);
    }

    private void addItem(Item item){
        this.item=item;
        item.getCategoryItems().add(this);
    }

}
