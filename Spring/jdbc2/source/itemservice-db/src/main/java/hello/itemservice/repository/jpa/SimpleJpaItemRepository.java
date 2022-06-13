package hello.itemservice.repository.jpa;

import hello.itemservice.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SimpleJpaItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByItemNameContaining(String itemName);
    List<Item> findByPriceLessThanEqual(Integer price);
    List<Item> findByItemNameContainingAndPriceLessThanEqual(String itemName, Integer price);
    @Query("select i from Item i where i.itemName like %:itemName% and i.price <= :price")
    List<Item> findItems(String itemName, Integer price);

}
