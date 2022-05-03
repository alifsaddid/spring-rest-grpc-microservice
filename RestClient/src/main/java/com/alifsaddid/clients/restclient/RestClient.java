package com.alifsaddid.clients.restclient;

import com.alifsaddid.clients.restclient.requests.CheckIsEvenRequest;
import com.alifsaddid.clients.restclient.requests.model.ServiceRequest;
import com.alifsaddid.clients.restclient.responses.CheckIsEvenResponse;
import com.alifsaddid.clients.restclient.responses.model.ServiceResponse;

public class RestClient extends BaseClient<ServiceResponse> {

    private static RestClient restClient;

    public static RestClient getInstance() {
        if (restClient == null) {
            restClient = new RestClient();
        }
        return restClient;
    }

    public CheckIsEvenResponse checkIsEven(int number) {
        ServiceRequest request = new CheckIsEvenRequest()
                .withNumber(number)
                .setUri("check");

        CheckIsEvenResponse response = invoke(request, CheckIsEvenResponse.class);
        return response;
    }

}