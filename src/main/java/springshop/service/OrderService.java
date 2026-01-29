package springshop.service;

import org.springframework.stereotype.Service;
import springshop.dto.OrderForm;
import springshop.model.Delivery;
import springshop.model.Order;
import springshop.model.OrderItem;

import java.util.List;
import java.util.Map;

public interface OrderService {

/*
    Long createDelivery(Delivery delivery);
    Long createOrder(Order order);  // 주문 생성
    Long createOrderItem(OrderItem orderItem);  // 주문 상품 정보 생성
    void cancelOrder(Long orderId); // 주문 취소

*/
    /**
     * 주문생성 (통합 트랜잭션)
     * - 배송정보 저장
     * - 주문 정보 저장
     * - 재고 검증 및 차감
     * - 주문 상품 정보 생성
     */
    Long createOrder(OrderForm orderForm);

    /**
     * 주문 취소
     * - 주문 상태 변경
     * - 재고 복구
     */
    void cancelOrder(Long orderId);

    List<Map<String, Object>> getOrders(String member, String orderStatus);


}
