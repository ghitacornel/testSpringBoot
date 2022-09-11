package beans.controller;

import beans.controller.model.DeleteRequest;
import beans.controller.model.LoginData;
import beans.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String delete(@ModelAttribute(value="deleteRequest") DeleteRequest request, Model model) {
        service.deleteById(request.getId());
        model.addAttribute("items", service.findAll());
        return "itemsPage";
    }

}
