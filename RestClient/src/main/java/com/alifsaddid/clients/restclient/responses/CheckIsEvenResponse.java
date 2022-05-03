package com.alifsaddid.clients.restclient.responses;

import com.alifsaddid.clients.restclient.responses.model.ServiceResponse;

public class CheckIsEvenResponse extends ServiceResponse {
    private boolean isEven;

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean even) {
        isEven = even;
    }
}
