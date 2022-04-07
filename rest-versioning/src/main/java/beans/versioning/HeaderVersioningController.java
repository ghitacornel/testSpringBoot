package beans.versioning;


import beans.versioning.model.VersionedModel;
import beans.versioning.model.VersionedModelV1;
import beans.versioning.model.VersionedModelV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST endpoint versioning using HTTP headers
 */
@RestController
@RequestMapping(value = "version/header")
public class HeaderVersioningController {

    @GetMapping(value = "invoke", headers = "X-API-VERSION=1")
    public VersionedModelV1 studentV1() {
        return new VersionedModelV1("Bob Charlie");
    }

    @GetMapping(value = "invoke", headers = "X-API-VERSION=2")
    public VersionedModelV2 studentV2() {
        return new VersionedModelV2("Bob", "Charlie");
    }

    /**
     * no header => default use latest
     */
    @GetMapping(value = "invoke")
    public VersionedModel student() {
        return new VersionedModel(1, "Bob", "Charlie");
    }

}
