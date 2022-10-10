package com.example.jpashop.domain;

import com.example.jpashop.vo.MemberForm;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Setter
@NoArgsConstructor
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name="member_id")
    private Long id;

    private String name;

    @Embedded
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "member")
    private List<Order> orders = new ArrayList<>();

    public Member(MemberForm memberForm) {
        this.address =
                new Address(memberForm.getCity(), memberForm.getStreet(), memberForm.getZipcode());
        this.name = memberForm.getName();
    }
}
