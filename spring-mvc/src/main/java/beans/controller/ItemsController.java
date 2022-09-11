package beans.controller;

import beans.controller.model.CreateItemRequest;
import beans.controller.model.DeleteRequest;
import beans.controller.model.LoginData;
import beans.service.ItemService;
import beans.service.model.ItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService service;

    @PostMapping("/items")
    public String greeting(@ModelAttribute("loginData") LoginData loginData, Model model) {

        // TODO add security check
        log.error("user logged with credentials : user = " + loginData.getUser() + " ; pass = " + loginData.getPass());

        model.addAttribute("items", service.findAll());
        return "itemsPage";
    }

    @PostMapping("/items/delete")
    public String delete(@ModelAttribute(value = "deleteRequest") DeleteRequest request, Model model) {
        service.deleteById(request.getId());
        model.addAttribute("items", service.findAll());
        return "itemsPage";
    }

    @GetMapping("/items/gotoCreatePage")
    public String gotoCreatePage() {
        return "itemCreatePage";
    }

    @PostMapping("/items/create")
    public String create(@ModelAttribute(value = "createItemRequest") CreateItemRequest request, Model model) {
        ItemModel itemModel = ItemModel.builder()
                .name(request.getName())
                .quantity(request.getQuantity())
                .price(request.getPrice())
                .registerDate(new Date())
                .build();
        service.save(itemModel);
        model.addAttribute("items", service.findAll());
        return "itemsPage";
    }

}
