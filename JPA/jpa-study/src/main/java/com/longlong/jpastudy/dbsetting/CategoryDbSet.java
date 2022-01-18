package com.longlong.jpastudy.dbsetting;

import com.longlong.jpastudy.vo.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-05-20
 * Time: 오후 8:56
 */
public class CategoryDbSet {
    public static void setting(EntityManager em) throws Exception {
        categorySetting(em);
    }

    private static void categorySetting(EntityManager em) {
        List<Category> list = new ArrayList<>();

        // level 0
        Category food = new Category("식품");
        list.add(food);

        // level 1 (식품 하위)
        Category fruit = new Category("과일",food);
        list.add(fruit);
        Category vegetable = new Category("채소",food);
        list.add(vegetable);
        Category meat = new Category("고기", food);
        list.add(meat);

        // level 2 (과일 하위)
        Category apple = new Category("사과", fruit);
        list.add(apple);
        Category banana = new Category("바나나", fruit);
        list.add(banana);
        // level 2 (고기 하위)
        Category pork = new Category("돼지고기", meat);
        list.add(pork);
        Category beef = new Category("소고기", meat);
        list.add(beef);
        Category chicken = new Category("닭고기", meat);
        list.add(chicken);

        //level 3 (돼지고기 하위)
        Category boiledPork = new Category("수육", pork);
        list.add(boiledPork);
        Category porkBelly = new Category("삼겹살", pork);
        list.add(porkBelly);

        // db반영
        for (Category category : list) {
            em.persist(category);
        }
    }
}
