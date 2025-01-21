package com.korea.shop.domain;

import aj.org.objectweb.asm.commons.InstructionAdapter;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString(exclude = {"parent","childe","categoryItems"}) // toString 메서드에서 제외시킬 양방향참조가 일어나는 객체
public class Category {

    @Id
    @GeneratedValue
    @Column(name = "category_id") // 테이블 저장 컬럼이름
    private Long id;

    private String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<CategoryItem> categoryItems = new ArrayList<>();

    // 셀프 참조

    // 상위 카테고리
    @ManyToOne @JoinColumn(name = "parent_id") // FK 설정
    private  Category parent;

    // 하위 카테고리
    @OneToMany(mappedBy = "parent")
    private  List<Category> childe = new ArrayList<>();

    // 카테고리 목록 추가
    private void addChildCategory(Category category){
        
        // 하위 카테고리 목록에 없으면 추가
        if (!this.childe.contains(category)) {
            // this(현재 객체)와 category는 다른 것이다 조심할것 순서 특히!
            this.childe.add(category);
            category.setParent(this);
        }
    }

    private void removeChildCategory(Category category){

        // 하위 카테고리 목록에 없으면 추가
        if (this.childe.contains(category)) {
            this.childe.remove(category);
            category.setParent(null);
        }
    }

    private void setParent (Category parent){
        this.parent = parent;
    };
}
