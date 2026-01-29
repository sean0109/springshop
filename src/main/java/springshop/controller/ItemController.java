package springshop.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springshop.dto.ItemForm;
import springshop.model.ItemType;
import springshop.model.Item;
import springshop.service.ItemService;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/items/new")
    public String createForm(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "items/createItemForm";  // 신규저장, 수정 통합 폼
    }

    // 저장
    @PostMapping("/items/new")
    public String saveItem(@Valid ItemForm form, BindingResult result, Model model) {

        if (result.hasErrors()) {
            // Spring이 자동으로 model.addAttribute("itemForm", form) 수행
            // 폼 다시 보여주면서 에러 출력
            return "items/createItemForm";
        }

        //checkPoint_나중에 로직 추가 ( dtype 따라 로직 처리)
        ItemType itemType = ItemType.from(form.getDtype());

        Item item = Item.builder()
                .itemId(form.getItemId())   // null 이면 신규, 값이 있으면 신규등록
                .name(form.getName())
                .etc(form.getEtc())
                .price(form.getPrice())
                .stockQuantity(form.getStockQuantity())
                .dtype(form.getDtype())
                .author(form.getAuthor())
                .isbn(form.getIsbn())
                .build();

        itemService.saveItem(item);

        return "redirect:/";
    }

    // 목록
    @GetMapping("/items")
    public String getList(Model model) {

        List<Item> list = itemService.findAllItems();
        model.addAttribute("items", list);

        return "items/itemList";
    }

    // 수정
    @PostMapping("/items/modify")
    public String modifyItem(@RequestParam Long itemId, Model model) {

        Item item = itemService.findItemById(itemId);

        ItemForm itemForm = ItemForm.builder()
                .name(item.getName())
                .itemId(item.getItemId())
                .dtype(item.getDtype())
                .author(item.getAuthor())
                .isbn(item.getIsbn())
                .price(item.getPrice())
                .stockQuantity(item.getStockQuantity())
                .build();

        model.addAttribute("itemForm", itemForm);

        return "items/createItemForm";
    }

}
