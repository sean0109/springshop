package springshop.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import springshop.model.Item;
import springshop.service.ItemService;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final ItemService itemService;

    @RequestMapping("/")
    public String home(Model model) {
        log.info("HomeController enter");

        // 상품리스트 홈 화면에 출력
        List<Item> itemList = itemService.findAllItems();
        model.addAttribute("items", itemList);
        return "home";

        // return "home_mobile";   // checkPoint_모바일 화면
    }

    @PostMapping("/")
    @ResponseBody
    public List<Item> search( @RequestBody Map<String, String> body) {
        log.info(body.get("name"));
        log.info(body.get("price"));
        log.info(body.get("stockQuantity"));

        return itemService.findItemByName(body.get("name"));
    }
}
