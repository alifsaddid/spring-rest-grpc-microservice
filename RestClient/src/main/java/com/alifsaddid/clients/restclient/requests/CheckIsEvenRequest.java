package com.alifsaddid.clients.restclient.requests;

import com.alifsaddid.clients.restclient.requests.model.ServiceRequest;

public class CheckIsEvenRequest extends ServiceRequest {

    private int number;

    public CheckIsEvenRequest withNumber(int number) {
        this.addToParameterMap("number", Integer.toString(number));
        return this;
    }

}
