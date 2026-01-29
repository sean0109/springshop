package springshop.dto;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderForm {

    Long orderId;
    @NotNull(message = "회원 선택은 필수 입니다.")
    Long memberId;
    @NotNull(message = "상품이 선택 되지 않았습니다.")
    Long itemId;
    int orderCount;

    @Builder
    public OrderForm(Long orderId, Long memberId, Long itemId, int orderCount) {
        this.orderId = orderId;
        this.memberId = memberId;
        this.itemId = itemId;
        this.orderCount = orderCount;
    }
}
