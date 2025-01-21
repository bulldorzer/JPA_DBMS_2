package com.korea.shop.domain.item;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@Getter
@DiscriminatorValue("A") // 구분자
public class Album extends Item{
    private String artist;
    private String ect;
}
