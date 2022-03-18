package beans.rest.clients.feign;

import feign.RequestLine;

public interface ExternalServiceContract {

    @RequestLine("GET /externalService")
    String invokeExternalService();

}
