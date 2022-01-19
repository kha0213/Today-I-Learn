package com.test.toyproject1.search;

import com.test.toyproject1.entity.valueType.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter @Setter
public class OrderSearch {

    private String memberName;      //회원 이름
    private OrderStatus orderStatus;//주문 상태(ORDER,CANCEL)


}
