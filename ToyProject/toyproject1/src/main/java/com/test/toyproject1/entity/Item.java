package com.test.toyproject1.entity;

import com.test.toyproject1.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "ITEM")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
    @Id @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ToString.Exclude
    @ManyToMany
    @JoinTable(name = "CATEGORY_ITEM",
            joinColumns = @JoinColumn(name = "CATEGORY_ID"),
    inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
    private List<Category> categories = new ArrayList<>();

    // 비즈니스 로직

    /**
     * 재고 추가
     * @param quantity
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }

    /**
     * 재고 삭제
     * @param quantity
     * @throws NotEnoughStockException
     */
    public void removeStock(int quantity) throws NotEnoughStockException {
        int restStock = this.stockQuantity - quantity;
        if (restStock < 0) { // 재고가 음수면 에러
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantity = restStock;
    }
    
}