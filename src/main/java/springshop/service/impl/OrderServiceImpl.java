package springshop.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springshop.dto.OrderForm;
import springshop.exception.order.OrderCountException;
import springshop.mapper.ItemMapper;
import springshop.mapper.MemberMapper;
import springshop.mapper.OrderMapper;
import springshop.model.*;
import springshop.model.code.DeliveryStatus;
import springshop.model.code.OrderStatus;
import springshop.service.OrderService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberMapper memberMapper;
    private final OrderMapper orderMapper;
    private final ItemMapper itemMapper;


    @Override
    public Long createOrder(OrderForm orderForm) {

        // 1. 회원 주소 조회
        Member orderMember = memberMapper.findById(orderForm.getMemberId());

        // 2. 상품 조회 ( 재고 확인 )
        Item item = itemMapper.findById(orderForm.getItemId());

        // 3. 재고 검증
        if (item.getStockQuantity() < orderForm.getOrderCount()) {
            throw new OrderCountException("주문 수량이 재고 수량을 초과하였습니다.", item.getStockQuantity());
        }

        // 4. 재고 차감
        orderMapper.decreaseStock(item.getItemId(), orderForm.getOrderCount());

        // 1. 배송 정보 저장 (delivery) - deliveryId 채번
        Delivery delivery = Delivery.builder()
                .status(DeliveryStatus.READY.getCode())
                .deliveryAddress(orderMember.getAddress())
                .build();

        orderMapper.insertDelivery(delivery);

        // 2. 주문 정보 저장 (order)
        Order order = Order.builder().
                memberId(orderMember.getMemberId())
                .deliveryId(delivery.getDeliveryId())
                .orderDate(new Date())
                .Status(OrderStatus.READY.getCode())
                .build();
        orderMapper.insertOrder(order);

        // 3. 주문 상품 정보 저장 (가격 계산)
        int orderPrice = item.getPrice() * orderForm.getOrderCount();
        OrderItem orderItem = OrderItem.builder()
                .orderId(order.getOrderId())
                .itemId(orderForm.getItemId())
                .orderPrice(orderPrice)
                .count(orderForm.getOrderCount())
                .build();
        orderMapper.insertOrderItem(orderItem);

        return order.getOrderId();
    }

    @Override
    public void cancelOrder(Long orderId) {

    }

    @Override
    public List<Map<String, Object>> getOrders(String memberName, String orderStatus) {

        return orderMapper.getOrdersList(memberName, orderStatus);
    }


/*    @Override
    public Long createDelivery(Delivery delivery) {
        // 배송 정보 저장 (selectKey로 deliveryId 자동 설정)
        orderMapper.insertDelivery(delivery);

        // 생성된 deliveryId 반환
        return delivery.getDeliveryId();
    }

    @Override
    public Long createOrder(Order order) {
        // 주문 정보 저장 (selectKey로 orderId 자동 설정)
        orderMapper.insertOrder(order);

        // 생성된 orderId 반환
        return order.getOrderId();
    }

    @Override
    public Long createOrderItem(OrderItem orderItem) {
        // 상품 가격 구하기
        Item item = itemMapper.findById(orderItem.getItemId());
        int totalPrice = orderItem.getCount() * item.getPrice();

        // 주문 상품 정보 저장
        orderMapper.insertOrderItem(orderItem);


        return null;
    }


    @Override
    public void cancelOrder(Long orderId) {
        // 주문 상태를 CANCELED로 변경
        orderMapper.updateOrderStatus(orderId, OrderStatus.CANCELED.getCode());
    }*/
}
