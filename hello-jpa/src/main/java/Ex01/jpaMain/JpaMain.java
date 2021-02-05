package Ex01.jpaMain;

import Ex01.Delivery;
import Ex01.Member;
import Ex01.Orders;
import Ex01.Type;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;

/**
 * 2021-02-03
 * Created by tatujjang@gmail.com
 * Github : https://github.com/kha0213/
 */
public class JpaMain {
    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {

            Member member1 = new Member();
            member1.setName("멤버1");
            member1.setCity("서울");
            member1.setStreet("문정로");
            member1.setZipcode("158-200");
            em.persist(member1);

            Delivery delivery = new Delivery();
            delivery.setCity("서울");
            delivery.setStreet("문정로");
            delivery.setZipcode("158-200");
            delivery.setStatus(Type.PREPARE);

            em.persist(delivery);

            Orders order = new Orders();
            order.setMember(member1);
            order.setDelivery(delivery);

            order.setOrderDate(new Date());
            order.setStatus(Type.ORDER);

            em.persist(order);

            System.out.println("memberOrder:"+member1.getOrdersList().size());








            tx.commit();
        } catch (Exception e) {
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println("■■■■■■■■■■■■■■■ERROR■■■■■■■■■■■");
            System.out.println(e.getMessage());
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            System.out.println("■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■■");
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();
    }
}
