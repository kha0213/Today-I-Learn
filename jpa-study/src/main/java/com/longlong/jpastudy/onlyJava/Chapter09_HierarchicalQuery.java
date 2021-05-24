package com.longlong.jpastudy.onlyJava;

import com.longlong.jpastudy.dbsetting.CategoryDbSet;
import com.longlong.jpastudy.dto.CategoryHierarchicalDto;
import com.longlong.jpastudy.vo.Category;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class Chapter09_HierarchicalQuery {
    public static void main(String[] args) {
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("start!!");
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("simple-jpa-application");
        EntityManager em = emf.createEntityManager();


        System.out.println("■■■■■■■■■■■■■■■■■■■■시작~~~~~");

        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            // DB 세팅
            CategoryDbSet.setting(em);

            logic(em, tx);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("■■■■■■■■■■■■■■■■■■■■ ERROR   : " + e.getMessage());
        } finally {
            em.close();

        }

        emf.close();
    }

    public static void logic(EntityManager em, EntityTransaction tx) throws Exception {
        em.flush();
        em.clear();
        // 일반 전체 조회문 11개
        final List<Category> resultList1 = em.createQuery("select c from Category c", Category.class)
                .getResultList();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("resultList1 [size] : " + resultList1.size());
        resultList1.stream().forEachOrdered(System.out::println);

        // 이너조인 10개 (parent 없는거 제외)
        final List<Category> resultList2 =
                em.createQuery("select c1 from Category As c1 inner join Category As c2 on c1.parent = c2", Category.class)
                .getResultList();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("resultList2 [size] : " + resultList2.size());
        resultList2.stream().forEachOrdered(System.out::println);

        // left 조인 11개
        final List<Category> resultList3 =
                em.createQuery("select c1 from Category As c1 left join Category As c2 on c1.parent = c2", Category.class)
                        .getResultList();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("resultList3 [size] : " + resultList3.size());
        resultList3.stream().forEachOrdered(System.out::println);

        // 트리구조 조회 네이티브 쿼리
        String query = "WITH RECURSIVE CTE AS (\n" +
                "    SELECT\n" +
                "        c.category_id, c.level, c.name, c.parent_category_id,\n" +
                "        c.name as out_name\n" +
                "    FROM category c\n" +
                "    WHERE c.level = 0\n" +
                "    UNION ALL\n" +
                "\n" +
                "    SELECT\n" +
                "        a.category_id, a.level, a.name, a.parent_category_id,\n" +
                "        CONCAT(b.out_name,' > ',a.name)  as out_name\n" +
                "    FROM category a\n" +
                "    INNER JOIN CTE b ON a.parent_category_id = b.category_id\n" +
                ")\n" +
                "SELECT * FROM CTE\n" +
                "order by out_name";
        final List<CategoryHierarchicalDto> resultList4 =
                em.createNativeQuery(query, "CategoryHierarchicalDto")
                        .getResultList();
        System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
        System.out.println("resultList4 [size] : " + resultList4.size());
        resultList4.stream().forEachOrdered(System.out::println);

    }
}
