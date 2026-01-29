package springshop.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class Delivery {

    private Long deliveryId;
    private String status;
    private Address deliveryAddress;

    @Builder
    public Delivery(Long deliveryId, String status, Address deliveryAddress) {
        this.deliveryId = deliveryId;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
    }


}
