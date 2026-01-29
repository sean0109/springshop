package springshop.controller;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import springshop.dto.OrderForm;
import springshop.mapper.OrderMapper;
import springshop.model.Address;
import springshop.model.Item;
import springshop.model.Member;
import springshop.service.ItemService;
import springshop.service.MemberService;
import springshop.service.OrderService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional  // 테스트 후 콜백
class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderService orderService;
    @Autowired
    private MemberService memberService;
    @Autowired
    private ItemService itemService;

    // 테스트 멤버
    private Member testMember;
    // 테스트 아이템
    private Item testItem;
    // 주문 생성 폼
    private OrderForm orderForm;

    @BeforeEach
    void setUp() {
        // 테스트 회원 생성
        testMember = Member.builder()
                .name("테스트")
                .address(Address.builder()
                        .city("부산")
                        .street("거리")
                        .zipcode("123123")
                        .build())
                .build();
        memberService.join(testMember);
        // 테스트 아이템 생성
        testItem = Item.builder()
                .name("테스트상품")
                .price(20000)
                .stockQuantity(5)
                .dtype("BOOK")
                .author("테스트저자")
                .isbn("테스트isbn")
                .director("테스트디렉터")
                .actor("테스트액터")
                .artist("테스트아티스트")
                .etc("테스트etc")
                .build();
        itemService.saveItem(testItem);

        // 주문 생성 폼 생성
        orderForm = OrderForm.builder()
                .memberId(testMember.getMemberId())
                .itemId(testItem.getItemId())
                .orderCount(3)
                .build();
    }

    @Test
    @DisplayName("테스트 주문 정보 생성")
    void createOrder() throws Exception {
        // when
        orderService.createOrder(orderForm);

        // then (수량 테스트 - DB에서 다시 조회)
        Item updatedItem = itemService.findItemById(testItem.getItemId());
        Assertions.assertThat(updatedItem.getStockQuantity()).isEqualTo(2);
    }
}