package beans.clients.feign;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ExternalServiceContract {

    @RequestLine("GET /externalService/{input}")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokeGet(@Param("input") String input);

    @RequestLine("POST /externalService")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokePost(PersonRequestDto inputModel);

}
