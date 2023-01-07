package beans.controller;

import beans.controller.model.CreateItemRequest;
import beans.controller.model.DeleteRequest;
import beans.controller.model.LoginData;
import beans.controller.session.UserDetailsSession;
import beans.service.ItemService;
import beans.service.model.ItemModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemsController {

    private final ItemService service;
    private final UserDetailsSession userDetailsSession;

    @GetMapping("/items")
    public String gotoItemsPage(Model model) {
        model.addAttribute("items", service.findAll());
        model.addAttribute("loggedUser", userDetailsSession.getUser());
        return "itemsPage";
    }

    @PostMapping("/items/delete")
    public String deleteItem(@ModelAttribute(value = "deleteRequest") DeleteRequest request, Model model) {
        service.deleteById(request.getId());
        model.addAttribute("items", service.findAll());
        return "itemsPage";
    }

    @GetMapping("/items/gotoCreatePage")
    public String gotoCreateItemPage() {
        return "itemCreatePage";
    }

    @PostMapping("/items/create")
    public String createItem(@ModelAttribute(value = "createItemRequest") CreateItemRequest request, Model model) {
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
