package com.longlong.jpastudy.vo;

import com.longlong.jpastudy.vo.item.embedded.Address;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Entity
@Table(name = "MEMBER")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id @GeneratedValue
    @Column(name = "Member_Id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

//    @ElementCollection
//    @CollectionTable(
//            name = "ADDRESS",
//            joinColumns = @JoinColumn(name = "Member_Id"))
//    private List<Address> addressHistory = new ArrayList<>();


    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Orders> orders = new HashSet<>();


    public Member(String name, String city, String street, String zipcode) {
        this.name = name;
        this.address = new Address(city, street, zipcode);
    }
    // 연관관계 편의 메소드
    public void addOrder(Orders orderB) {
        this.orders.add(orderB);
        orderB.setMember(this);
    }
}
