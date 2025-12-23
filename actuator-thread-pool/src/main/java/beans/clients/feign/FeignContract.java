package beans.clients.feign;

import beans.clients.external.PersonResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.validation.annotation.Validated;

@Validated
public interface FeignContract {

    @RequestLine("GET /externalService/{path}?parameter={query}")
    @Headers("Content-Type: application/json")
    PersonResponseDto invokeGET(@Param("path") String path, @Param("query") String query);

}
