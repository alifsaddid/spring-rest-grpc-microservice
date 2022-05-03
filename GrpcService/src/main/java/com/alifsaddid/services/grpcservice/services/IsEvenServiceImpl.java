package com.alifsaddid.services.grpcservice.services;

import com.alifsaddid.proto.requests.EvenNumberRequest;
import com.alifsaddid.proto.responses.EvenNumberResponse;
import com.alifsaddid.proto.services.IsEvenServiceGrpc;
import com.alifsaddid.services.grpcservice.streams.EvenNumberRequestStream;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class IsEvenServiceImpl extends IsEvenServiceGrpc.IsEvenServiceImplBase {

    @Override
    public void checkIsEvenUnary(EvenNumberRequest request, StreamObserver<EvenNumberResponse> responseObserver) {
        int number = request.getNumber();
        EvenNumberResponse response = EvenNumberResponse
                .newBuilder()
                .setIsEven(number % 2 == 0)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<EvenNumberRequest> checkIsEvenBidirectionalStream(StreamObserver<EvenNumberResponse> responseObserver) {
        return new EvenNumberRequestStream(responseObserver);
    }
}
