package beans.clients.feign;

import beans.clients.model.ClientResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FeignContract {

    @RequestLine("GET /externalService/{path}?parameter={query}")
    @Headers("Content-Type: application/json")
    ClientResponseDto invokeGET(@Param("path") String path, @Param("query") String query);

}
