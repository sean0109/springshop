package springshop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import springshop.model.Delivery;
import springshop.model.Order;
import springshop.model.OrderItem;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    // 배송 정보 저장
    void insertDelivery(Delivery delivery);
    // 주문 정보 저장
    void insertOrder(Order order);
    // 주문상품 정보 저장
    void insertOrderItem(OrderItem orderItem);
    // 상품 재고 차감
    void decreaseStock(@Param("itemId") Long itemId, @Param("quantity") int count );    // 재고 수량 차감
    // 주문 상태 변경 (주문 취소 시)
    void updateOrderStatus(@Param("orderId") Long orderId, @Param("status") String status);
    Order findById(Long orderId);

    List<Map<String, Object>> getOrdersList(@Param("memberName") String memberName, @Param("orderStatus") String orderStatus);

}
