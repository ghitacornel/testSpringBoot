package beans.controller.session;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.annotation.SessionScope;

@Data
@NoArgsConstructor
@Component("userDetailsSession")
@SessionScope
public class UserDetailsSession {

    @ModelAttribute("loggedUser")
    public String loggedUser() {
        return user;
    }

    private String user;
    private String pass;

}
