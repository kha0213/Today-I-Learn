package com.example.jpashop.domain;

import com.example.jpashop.vo.BookForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B")
@Getter @Setter
@NoArgsConstructor
public class Book extends Item {
    private String author;
    private String isbn;

    public Book(BookForm bookForm) {
        this.setName(bookForm.getName());
        this.setPrice(bookForm.getPrice());
        this.setStockQuantity(bookForm.getStockQuantity());
        this.setAuthor(bookForm.getAuthor());
        this.setIsbn(bookForm.getIsbn());
    }
}
