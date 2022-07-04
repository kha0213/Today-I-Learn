package hello.springtx.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    // 예금액
    private Integer saveMoney;

    public Member(String name, Integer saveMoney) {
        this.name = name;
        this.saveMoney = saveMoney;
    }
}
