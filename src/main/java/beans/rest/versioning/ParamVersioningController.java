package beans.rest.versioning;

import beans.rest.versioning.model.StudentV1;
import beans.rest.versioning.model.StudentV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "version/param")
public class ParamVersioningController {

    @GetMapping(value = "student", params = "version=1")
    public StudentV1 studentV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "student", params = "version=2")
    public StudentV2 studentV2() {
        return new StudentV2("Bob", "Charlie");
    }

}
