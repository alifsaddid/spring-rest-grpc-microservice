package com.alifsaddid.clients.restclient;

import com.alifsaddid.clients.restclient.requests.model.ServiceRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

public abstract class BaseClient<R> {

    public static final String REQUEST_METHOD_POST = "POST";
    public static final String CONTENT_TYPE_JSON = "application/json";

    private ObjectMapper objectMapper = new ObjectMapper();

    public <S extends R> S invoke(ServiceRequest request, Class<S> responseClass) {
        String responseString = null;
        try {
            URLConnection connection = openConnection(
                    request.getUri(),
                    request.getRequestMethod(),
                    CONTENT_TYPE_JSON,
                    request.getParameterMap()
            );
            responseString = getResponse((HttpURLConnection) connection);
        } catch (Exception e) {

        }
        return responseClass.cast(constructResponse(responseString, responseClass));
    }

    private <S extends R> S constructResponse(String responseString, Class<S> responseClass) {
        S response = null;

        try {
            response = objectMapper.readValue(responseString, responseClass);
        } catch (Exception e) {
            try {
                response = responseClass.newInstance();
            } catch (Exception exception) {

            }
        }
        return response;
    }

    private String getResponse(HttpURLConnection connection) {
        String result = null;
        try {
            InputStream inputStream = null;
            if (connection.getResponseCode() < 400) {
                inputStream = connection.getInputStream();
            } else {
                inputStream = connection.getErrorStream();
            }
            result = getResponseString(inputStream);
        } catch (Exception e) {

        }
        return result;
    }

    private String getResponseString(InputStream inputStream) {
        StringBuilder result = new StringBuilder();

        try (BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
        } catch (Exception e) {
        }

        return result.toString();
    }

    private URLConnection openConnection(
            String uri,
            String requestMethod,
            String contentType,
            Map<String, String> paramMap
    ) {
        URL url;
        HttpURLConnection connection = null;
        try {
            url = new URL(buildUrl(uri, paramMap));
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(requestMethod);
            connection.setRequestProperty("Content-Type", contentType);
            connection.setDoOutput(true);
        } catch (Exception e) {

        }
        return connection;
    }

    private String buildUrl(
            String uri,
            Map<String, String> paramMap
    ) {
        StringBuilder builder = new StringBuilder();
        builder.append(buildParam(paramMap));
        return uri + "?" + builder.toString();
    }

    private String buildParam(Map<String, String > paramMap) {
        StringBuilder builder = new StringBuilder();
        int counter = 1;
        for (Map.Entry<String, String> entry : paramMap.entrySet()) {
            String paramValue = entry.getValue();
            String paramKey = entry.getKey();

            try {
                builder
                        .append(paramKey)
                        .append("=")
                        .append(URLEncoder.encode(paramValue, "UTF-8"));
                if (counter < paramMap.size()) {
                    builder.append("&");
                }
                counter++;
            } catch (Exception e) {

            }
        }
        return builder.toString();
    }

}
