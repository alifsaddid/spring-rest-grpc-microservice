package com.alifsaddid.clients.restclient.requests.model;

import java.util.HashMap;
import java.util.Map;

public abstract class ServiceRequest {
    private String uri = "http://localhost:8081/";
    private String requestMethod = "GET";
    private Map<String, String> parameterMap = new HashMap<>();

    public void addToParameterMap(String key, String value) {
        parameterMap.put(key, value);
    }

    public ServiceRequest setUri(String uri) {
        this.uri = this.uri + uri;
        return this;
    }

    public String getUri() {
        return uri;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Map<String, String> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String> parameterMap) {
        this.parameterMap = parameterMap;
    }

}
