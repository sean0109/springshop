package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class Order {

    private Long orderId;
    private Long memberId;
    private Long deliveryId;

    private LocalDateTime orderDate;
    private String status;

    @Builder
    public Order(Long memberId, Long deliveryId, LocalDateTime orderDate, String status) {
        this.memberId = memberId;
        this.deliveryId = deliveryId;
        this.orderDate = orderDate;
        this.status = status;

    }
}

