package beans.clients.feign;

import beans.external.ExternalRestServiceInputModel;
import beans.external.ExternalRestServiceOutputModel;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ExternalServiceContract {

    @RequestLine("GET /externalService/{input}")
    @Headers("Content-Type: application/text")
    String invokeGet(@Param("input") String input);

    @RequestLine("POST /externalService")
    @Headers("Content-Type: application/json")
    ExternalRestServiceOutputModel invokePost(ExternalRestServiceInputModel inputModel);

}
