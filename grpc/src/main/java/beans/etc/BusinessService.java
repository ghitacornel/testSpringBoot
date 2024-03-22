package beans.etc;

import external.service.ExternalServiceGrpc;
import external.service.RequestMessage;
import external.service.ResponseMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BusinessService {

    // multiple types of stubs available
    private final ExternalServiceGrpc.ExternalServiceBlockingStub stub;

    public ResponseDto invokeExternal() {

        // request
        RequestMessage request = RequestMessage.newBuilder()
                .setMessage("request body")
                .setFlag(true)
                .setCount(111)
                .build();

        // invoke
        ResponseMessage response = stub.invokeExternal(request);

        // process response
        return ResponseDto.builder()
                .content(response.getContent())
                .status(response.getStatus())
                .build();

    }

}
