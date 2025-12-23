package beans.clients.feign;

import beans.clients.model.ClientResponseDto;
import feign.Headers;
import feign.Param;
import feign.RequestLine;

public interface FeignContract {

    @RequestLine("GET /externalService/{path}")
    @Headers("Content-Type: application/json")
    ClientResponseDto invoke(@Param("path") String path);

}
