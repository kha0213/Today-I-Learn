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
@Getter
@Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "DELIVERY_ID")
    private Long id;

    private String city;

    private String street;

    private String zipcode;
    @Enumerated(EnumType.STRING)
    private Type status;
}
