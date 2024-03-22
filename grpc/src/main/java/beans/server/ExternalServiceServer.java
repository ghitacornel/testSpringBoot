package beans.server;

import external.service.ExternalServiceGrpc;
import external.service.RequestMessage;
import external.service.ResponseMessage;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
public class ExternalServiceServer extends ExternalServiceGrpc.ExternalServiceImplBase {

    @Override
    public void invokeExternal(RequestMessage request, StreamObserver<ResponseMessage> responseObserver) {

        ResponseMessage responseMessage = ResponseMessage.newBuilder()
                .setContent(request.getMessage() + " with response " + request.getCount())
                .setStatus(request.getFlag())
                .build();

        responseObserver.onNext(responseMessage);
        responseObserver.onCompleted();
    }
}
