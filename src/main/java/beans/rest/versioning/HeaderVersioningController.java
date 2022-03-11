package beans.rest.versioning;

import beans.rest.versioning.model.Student;
import beans.rest.versioning.model.StudentV1;
import beans.rest.versioning.model.StudentV2;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST endpoint versioning using HTTP headers
 */
@Hidden// do not expose it via Swagger due to issues on OpenAPI parsing of headers
@RestController
@RequestMapping(value = "version/header")
public class HeaderVersioningController {

    @GetMapping(value = "student", headers = "X-API-VERSION=1")
    public StudentV1 studentV1() {
        return new StudentV1("Bob Charlie");
    }

    @GetMapping(value = "student", headers = "X-API-VERSION=2")
    public StudentV2 studentV2() {
        return new StudentV2("Bob", "Charlie");
    }

    /**
     * no header => default use latest
     */
    @GetMapping(value = "student")
    public Student student() {
        return new Student(1, "Bob", "Charlie");
    }

}
