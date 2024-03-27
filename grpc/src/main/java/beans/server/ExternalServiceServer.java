package beans.server;

import external.service.ExternalServiceGrpc;
import external.service.RequestMessage;
import external.service.ResponseMessage;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

@Component
class ExternalServiceServer extends ExternalServiceGrpc.ExternalServiceImplBase {

    @Override
    public void invokeExternal(RequestMessage request, StreamObserver<ResponseMessage> responseObserver) {

        ResponseMessage responseMessage;

        if (request.getMessage().equalsIgnoreCase("blank")) {
            responseMessage = ResponseMessage.newBuilder()
                    .setContent("")// cannot set to null even with "optional"
                    .build();
        } else {
            responseMessage = ResponseMessage.newBuilder()
                    .setContent(request.getMessage() + " with response " + request.getCount())
                    .setStatus(request.getFlag())
                    .build();
        }

        ResponseMessage.newBuilder()
                .setContent(request.getMessage() + " with response " + request.getCount())
                .setStatus(request.getFlag())
                .build();

        responseObserver.onNext(responseMessage);
        responseObserver.onCompleted();
    }
}
