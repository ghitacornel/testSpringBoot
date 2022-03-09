package beans.rest.versioning;

import beans.rest.versioning.model.StudentV1;
import beans.rest.versioning.model.StudentV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "version/media-type")
public class VersioningMediaTypeController {

    @GetMapping(value = "student", produces = "application/vnd.company.app-v1+json")
    public StudentV1 studentV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "student", produces = "application/vnd.company.app-v2+json")
    public StudentV2 studentV2() {
        return new StudentV2("Bob", "Charlie");
    }

}
