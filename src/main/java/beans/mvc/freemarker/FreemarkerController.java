package beans.mvc.freemarker;

import beans.services.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/custom")
public class FreemarkerController {

    @Autowired
    BusinessService businessService;

    @GetMapping({""})
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String input) {
        model.addAttribute("namePlaceholder", businessService.calculateName(input));
        return "hello";
    }

}
