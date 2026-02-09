package springshop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springshop.dto.OrderForm;
import springshop.model.*;
import springshop.model.code.DeliveryStatus;
import springshop.model.code.OrderStatus;
import springshop.service.ItemService;
import springshop.service.MemberService;
import springshop.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String createForm(Model model) {
        addFormAttributes(model);
        model.addAttribute("orderForm", new OrderForm());

        return "order/createOrderForm";
    }

    @PostMapping("/order")
    public String createOrder(@Valid OrderForm orderForm, BindingResult result, Model model) {

        if (result.hasErrors()) {
            addFormAttributes(model);
            return "order/createOrderForm";
        }
        Long orderId = orderService.createOrder(orderForm);

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderList(@RequestParam(required = false) String memberName,
                            @RequestParam(required = false) String orderStatus,
                            Model model) {

        // 검색 조건
        List<Map<String,Object>> orders = orderService.getOrders(memberName, orderStatus);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    private void addFormAttributes(Model model) {
        List<Member> members = memberService.findMembers();
        List<Item> items = itemService.findAllItems();

        model.addAttribute("members", members);
        model.addAttribute("items", items);
    }

}
