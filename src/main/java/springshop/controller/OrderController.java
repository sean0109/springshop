package springshop.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springshop.model.Item;
import springshop.model.Member;
import springshop.service.ItemService;
import springshop.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final ItemService itemService;
    private final MemberService memberService;

    @GetMapping("/order")
    public String createOrder(Model model) {

        List<Member> memberList = memberService.findMembers();
        List<Item> itemList = itemService.findAllItems();

        model.addAttribute("members", memberList);
        model.addAttribute("items", itemList);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count ) {

        // 주문 생성





        return "redirect:/order";
    }
}