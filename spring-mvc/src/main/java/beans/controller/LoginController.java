package beans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String gotoLoginPage(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        return "loginPage";
    }
}
