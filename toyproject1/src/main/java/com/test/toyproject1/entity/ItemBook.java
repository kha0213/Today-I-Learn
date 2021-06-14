package com.test.toyproject1.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@NoArgsConstructor
@Getter @Setter
@Entity
@DiscriminatorValue("BOOK")
public class ItemBook extends Item {
    private String author;
    private String isbn;
}
