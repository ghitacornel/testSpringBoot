package beans.versioning;

import beans.versioning.model.VersionedModel;
import beans.versioning.model.VersionedModelV1;
import beans.versioning.model.VersionedModelV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST versioning using path prefix
 */
@RestController
@RequestMapping(value = "version/path")
public class PathVersioningController {

    @GetMapping("v1/invoke")
    public VersionedModelV1 studentV1() {
        return new VersionedModelV1("Bob Charlie");
    }

    @GetMapping("v2/invoke")
    public VersionedModelV2 studentV2() {
        return new VersionedModelV2("Bob", "Charlie");
    }

    /**
     * no prefix => default use latest
     */
    @GetMapping("invoke")
    public VersionedModel student() {
        return new VersionedModel(1, "Bob", "Charlie");
    }

}
