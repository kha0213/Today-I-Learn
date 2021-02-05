package Ex01;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 2021-02-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
@Entity
@Getter @Setter
public class Orders {
    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID")
    private Delivery delivery;

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;
    @Enumerated(EnumType.STRING)
    private Type status;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> items = new ArrayList<>();

    public void setMember(Member member) {
        if(this.member != null)
            this.member.getOrdersList().remove(this);
        this.member = member;
        member.getOrdersList().add(this);

    }
}
