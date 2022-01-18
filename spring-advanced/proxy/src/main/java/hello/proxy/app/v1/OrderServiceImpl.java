package hello.proxy.app.v1;

import lombok.RequiredArgsConstructor;

/**
 * 2021-11-22
 * Created By Kim Young Long
 * Blog : https://kha0213.github.io/
 * GitHub : https://github.com/kha0213
 */
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderServiceV1 {
    private final OrderRepositoryV1 orderRepository;

    @Override
    public void orderItem(String itemId) {
        orderRepository.save(itemId);
    }
}
