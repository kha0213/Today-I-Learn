package hello.jdbc.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Member {
    private String memberId;
    private int money;


    public Member(String memberId, int money) {
        this.memberId = memberId;
        this.money = money;
    }
}
