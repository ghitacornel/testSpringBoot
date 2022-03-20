package beans.mvc.freemarker;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "freemarker")
public class FreemarkerController {

    @GetMapping
    public String hello(Model model, @RequestParam(value = "name", required = false, defaultValue = "World") String input) {
        model.addAttribute("namePlaceholder", "domnul " + input);
        return "hello";
    }

}
