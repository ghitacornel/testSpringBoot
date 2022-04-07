package beans.rest.versioning.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * model latest version
 */
@Data
@AllArgsConstructor
public class VersionedModel {

    private Integer id;
    private String firstName;
    private String lastName;

}
