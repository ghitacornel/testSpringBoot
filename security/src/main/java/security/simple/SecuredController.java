package security.simple;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("secured")
@Secured("ADMIN")
public class SecuredController {

    @GetMapping
    public String doSomethingSecure() {
        return "secured answer";
    }
}
