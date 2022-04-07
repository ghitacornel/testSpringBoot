package beans.rest.versioning;

import beans.rest.versioning.model.VersionedModel;
import beans.rest.versioning.model.VersionedModelV1;
import beans.rest.versioning.model.VersionedModelV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller with REST endpoint versioning using HTTP header ACCEPT and custom media type
 */
@RestController
@RequestMapping(value = "version/media-type")
public class MediaTypeVersioningController {

    @GetMapping(value = "invoke", produces = "application/vnd.company.app-v1+json")
    public VersionedModelV1 studentV1() {
        return new VersionedModelV1("Bob Charlie");
    }

    @GetMapping(value = "invoke", produces = "application/vnd.company.app-v2+json")
    public VersionedModelV2 studentV2() {
        return new VersionedModelV2("Bob", "Charlie");
    }

    /**
     * no header version particle => default use latest
     */
    @GetMapping(value = "invoke", produces = "application/vnd.company.app+json")
    public VersionedModel student() {
        return new VersionedModel(1, "Bob", "Charlie");
    }

}
