package com.test.toyproject1.construct;

import com.test.toyproject1.entity.Item;
import com.test.toyproject1.entity.ItemBook;
import com.test.toyproject1.entity.Member;
import com.test.toyproject1.service.ItemService;
import com.test.toyproject1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class PostConstructExample {

    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;
    @PostConstruct
    public void init() throws IllegalAccessException {
        Member member1 = new Member("young1");
        memberService.join(member1);
        Member member2 = new Member("young2");
        memberService.join(member2);
        Member member3 = new Member("young3");
        memberService.join(member3);
        Member member4 = new Member("young4");
        memberService.join(member4);

        Item item1 = new ItemBook("JPA",12000,10);
        itemService.saveItem(item1);
        Item item2 = new ItemBook("JAVA",1000,1);
        itemService.saveItem(item2);
        Item item3 = new ItemBook("SPRING",30000,5);
        itemService.saveItem(item3);
    }
}
