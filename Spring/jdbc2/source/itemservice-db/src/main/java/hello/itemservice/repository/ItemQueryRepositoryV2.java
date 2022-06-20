package hello.itemservice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import hello.itemservice.domain.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static hello.itemservice.domain.QItem.item;

@Repository
@RequiredArgsConstructor
public class ItemQueryRepositoryV2 {

    private final EntityManager em;

    private final JPAQueryFactory factory;

    public ItemQueryRepositoryV2(EntityManager em) {
        this.em = em;
        this.factory = new JPAQueryFactory(em);
    }

    public List<Item> findAll(ItemSearchCond cond) {
        Integer maxPrice = cond.getMaxPrice();
        String itemName = cond.getItemName();
        return factory.select(item)
                .from(item)
                .where(
                        likeItemName(itemName), maxPrice(maxPrice)
                ).fetch();
    }
    private BooleanExpression likeItemName(String itemName) {
        if (StringUtils.hasText(itemName)) {
            return item.itemName.like("%" + itemName + "%");
        }
        return null;
    }
    private BooleanExpression maxPrice(Integer maxPrice) {
        if (maxPrice != null) {
            return item.price.loe(maxPrice);
        }
        return null;
    }
}
