package com.alifsaddid.services.gatewayservice.controllers;

import com.alifsaddid.clients.grpcclient.GrpcClient;
import com.alifsaddid.clients.restclient.RestClient;
import com.alifsaddid.clients.restclient.responses.CheckIsEvenResponse;
import com.alifsaddid.proto.responses.EvenNumberResponse;
import io.grpc.Grpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GatewayController {

    @Autowired
    private GrpcClient grpcClient;

    @GetMapping("/rest/{number}")
    public Map<Integer, Boolean> getEvenNumberWithRest(@PathVariable int number) {
        Map<Integer, Boolean> response = new HashMap<>();
        for (int i = 1; i <= number; i++) {
            CheckIsEvenResponse isEvenResponse = RestClient.getInstance().checkIsEven(i);
            response.put(i, isEvenResponse.isEven());
        }

        return response;
    }

    @GetMapping("/rpc/unary/{number}")
    public Map<Integer, Boolean> getEvenNumberWithRpcUnary(@PathVariable int number) {
        Map<Integer, Boolean> response = new HashMap<>();
        for (int i = 1; i <= number; i++) {
            EvenNumberResponse evenNumberResponse = grpcClient.getEvenNumberUnaryCall(i);
            response.put(i, evenNumberResponse.getIsEven());
        }

        return response;
    }

    @GetMapping("/rpc/bidirectional/{number}")
    public Map<Integer, Boolean> getEvenNumberWithRpcBidirectional(@PathVariable int number) {
        Map<Integer, Boolean> response = grpcClient.getEvenNumberBidirectionalCall(number);
        return response;
    }

}
