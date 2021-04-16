package com.longlong.jpastudy.vo;

import com.longlong.jpastudy.config.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Orders {
    @Id @GeneratedValue
    private Long id;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private Status status; // VIP. NORMAL

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    @OneToMany(mappedBy = "id")
    private List<OrderItem> orderItems = new ArrayList<>();

    public Orders(LocalDateTime orderDate, Status status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public void putMember(Member memberA) {
        memberA.getOrders().add(this);
        setMember(memberA);
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", status=" + status +
                ", member=" + member.getName() +
                ", orderItems size=" + orderItems.size() +
                '}';
    }
}
