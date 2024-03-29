package com.example.jpashop;

import com.example.jpashop.domain.Address;
import com.example.jpashop.domain.Book;
import com.example.jpashop.domain.Item;
import com.example.jpashop.domain.Member;
import com.example.jpashop.repository.ItemRepository;
import com.example.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RequiredArgsConstructor
@SpringBootApplication
public class JpashopApplication {
    
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;
    public static void main(String[] args) {
        SpringApplication.run(JpashopApplication.class, args);
    }


    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        Member member = new Member();
        member.setName("테스트1");
        member.setAddress(new Address("서울시","송파구","가락동"));
        memberRepository.save(member);
        Member member2 = new Member();
        member2.setName("테스트2");
        member2.setAddress(new Address("서울시","송파구","문정동"));
        memberRepository.save(member2);

        Book item = new Book();
        item.setName("JPA");
        item.setPrice(10000);
        item.setStockQuantity(10);
        item.setAuthor("김영롱");
        item.setIsbn("123132131");
        itemRepository.save(item);
    }

}
