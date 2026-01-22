package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OrderItem {

    private Long orderItemId;
    private Long orderId;
    private Long itemId;
    private int orderPrice;
    private int count;

    @Builder
    public OrderItem(Long orderId, Long itemId, int orderPrice, int count) {
        this.orderId = orderId;
        this.itemId = itemId;
        this.orderPrice = orderPrice;
        this.count = count;
    }
}
