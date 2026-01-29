package springshop.exception.order;

import lombok.Getter;

@Getter
public class OrderCountException extends OrderException {

    private final int stockCount;

    public OrderCountException(String message, int stockCount) {
        super(message);
        this.stockCount = stockCount;
    }
}
