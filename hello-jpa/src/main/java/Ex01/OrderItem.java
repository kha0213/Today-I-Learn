package Ex01;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 2021-02-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
@Entity
@Getter @Setter
public class OrderItem {
    @Id @GeneratedValue
    @Column(name = "ORDER_ITEM")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ORDER_ID")
    private Orders order;

    private int orderPrice;

    private int count;


}
