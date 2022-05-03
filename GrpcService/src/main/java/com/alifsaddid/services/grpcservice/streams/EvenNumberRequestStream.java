package com.alifsaddid.services.grpcservice.streams;

import com.alifsaddid.proto.requests.EvenNumberRequest;
import com.alifsaddid.proto.responses.EvenNumberResponse;
import io.grpc.stub.StreamObserver;

public class EvenNumberRequestStream implements StreamObserver<EvenNumberRequest> {

    private StreamObserver<EvenNumberResponse> evenNumberResponseStreamObserver;

    public EvenNumberRequestStream(StreamObserver<EvenNumberResponse> evenNumberResponseStreamObserver) {
        this.evenNumberResponseStreamObserver = evenNumberResponseStreamObserver;
    }

    @Override
    public void onNext(EvenNumberRequest evenNumberRequest) {
        int number = evenNumberRequest.getNumber();
        EvenNumberResponse response = EvenNumberResponse
                .newBuilder()
                .setNumber(number)
                .setIsEven(number % 2 == 0)
                .build();

        evenNumberResponseStreamObserver.onNext(response);
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onCompleted() {
        evenNumberResponseStreamObserver.onCompleted();
    }
}
