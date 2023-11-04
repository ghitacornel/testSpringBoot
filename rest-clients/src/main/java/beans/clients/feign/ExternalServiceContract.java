package beans.clients.feign;

import beans.external.PersonRequestDto;
import beans.external.PersonResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface ExternalServiceContract {

    @RequestLine("GET /externalService/{path}?parameter={query}")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokeGET(@Param("path") String path, @Param("query") String query);

    @RequestLine("POST /externalService")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokePOST(PersonRequestDto inputModel);

    @RequestLine("PATCH /externalService")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokePATCH(PersonRequestDto inputModel);
}
