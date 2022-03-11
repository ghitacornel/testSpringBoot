package beans.rest.versioning;

import beans.rest.versioning.model.VersionedModel;
import beans.rest.versioning.model.VersionedModelV1;
import beans.rest.versioning.model.VersionedModelV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST endpoint versioning using HTTP query params
 */
@RestController
@RequestMapping(value = "version/param")
public class ParamVersioningController {

    @GetMapping(value = "invoke", params = "version=1")
    public VersionedModelV1 studentV1() {
        return new VersionedModelV1("Bob Charlie");
    }

    @GetMapping(value = "invoke", params = "version=2")
    public VersionedModelV2 studentV2() {
        return new VersionedModelV2("Bob", "Charlie");
    }

    /**
     * no param => default use latest
     */
    @GetMapping(value = "invoke")
    public VersionedModel student() {
        return new VersionedModel(1, "Bob", "Charlie");
    }

}
