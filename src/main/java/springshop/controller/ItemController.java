package springshop.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class ItemController {

    @GetMapping("/items/new")
    public String createForm(Model model) {
        //model.addAttribute("form", new BookForm());
        return "items/createItemForm";
    }
}
