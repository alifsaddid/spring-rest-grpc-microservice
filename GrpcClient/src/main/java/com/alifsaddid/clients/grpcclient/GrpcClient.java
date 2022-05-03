package com.alifsaddid.clients.grpcclient;

import com.alifsaddid.clients.grpcclient.streams.EvenNumberResponseStream;
import com.alifsaddid.proto.requests.EvenNumberRequest;
import com.alifsaddid.proto.responses.EvenNumberResponse;
import com.alifsaddid.proto.services.IsEvenServiceGrpc;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

@Component
public class GrpcClient {

    @net.devh.boot.grpc.client.inject.GrpcClient("is-even-service")
    private IsEvenServiceGrpc.IsEvenServiceBlockingStub blockingStub;

    @net.devh.boot.grpc.client.inject.GrpcClient("is-even-service")
    private IsEvenServiceGrpc.IsEvenServiceStub asyncstub;

    public EvenNumberResponse getEvenNumberUnaryCall(int number) {
        EvenNumberRequest request = EvenNumberRequest
                .newBuilder()
                .setNumber(number)
                .build();

        EvenNumberResponse response = blockingStub.checkIsEvenUnary(request);
        return response;
    }

    public HashMap<Integer, Boolean> getEvenNumberBidirectionalCall(int number) {
        HashMap<Integer, Boolean> response = new HashMap<>();
        CountDownLatch latch = new CountDownLatch(1);
        StreamObserver<EvenNumberRequest> streamObserver = asyncstub.checkIsEvenBidirectionalStream(
                new EvenNumberResponseStream(response, latch)
        );

        for (int i = 1; i <= number; i++) {
            EvenNumberRequest request = EvenNumberRequest
                    .newBuilder()
                    .setNumber(i)
                    .build();
            streamObserver.onNext(request);
        }

        streamObserver.onCompleted();

        try {
            latch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

}
