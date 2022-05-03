package com.alifsaddid.clients.grpcclient.streams;

import com.alifsaddid.proto.responses.EvenNumberResponse;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class EvenNumberResponseStream implements StreamObserver<EvenNumberResponse> {

    private HashMap<Integer, Boolean> response = new HashMap<>();
    private CountDownLatch latch;

    public EvenNumberResponseStream(HashMap<Integer, Boolean> response, CountDownLatch latch) {
        this.response = response;
        this.latch = latch;
    }

    @Override
    public void onNext(EvenNumberResponse evenNumberResponse) {
        response.put(evenNumberResponse.getNumber(), evenNumberResponse.getIsEven());
    }

    @Override
    public void onError(Throwable throwable) {
        latch.countDown();
    }

    @Override
    public void onCompleted() {
        latch.countDown();
    }
}
