package com.test.toyproject1.repository;

import com.test.toyproject1.entity.Item;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ItemRepository {
    @PersistenceContext
    EntityManager em;

    /**
     * 상품 저장
     *
     * @param item
     */
    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            em.merge(item);
        }
    }

    /**
     * 상품 단 건 조회
     *
     * @param id
     * @return
     */
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    /**
     * 상품 전체 조회
     * @return
     */
    public List<Item> findAll() {
        return em.createQuery("select i from Item as i")
                .getResultList();
    }

}
