package beans.controller;

import beans.controller.model.LoginData;
import beans.controller.session.UserDetailsSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserDetailsSession userDetailsSession;

    @GetMapping("/login")
    public String gotoLoginPage() {
        return "loginPage";
    }

    @PostMapping("/postLogin")
    public ModelAndView postLogin(@ModelAttribute("loginData") LoginData loginData) {

        // TODO add security check
        log.error("user logged with credentials : user = " + loginData.getUser() + " ; pass = " + loginData.getPass());

        // TODO add stuff on session
        userDetailsSession.setUser(loginData.getUser());
        userDetailsSession.setPass(loginData.getPass());

        // forward to the actual first page
        return new ModelAndView("redirect:/items");
    }

}
