package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class Order {

    private Long orderId;
    private Long memberId;
    private Long deliveryId;
    private Date orderDate;
    private String status;

    @Builder
    public Order(Long orderId, Long memberId, Long deliveryId, Date orderDate,
                 String Status) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.deliveryId = deliveryId;
        this.orderDate = orderDate;
        this.status = Status;
    }


}
