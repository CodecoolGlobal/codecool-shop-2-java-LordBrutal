package com.codecool.shop.service;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class RequestHandlerService {

    private final HttpServletRequest request;

    public RequestHandlerService(HttpServletRequest request) {
        this.request = request;
    }

    public String readRequestBody() {
        StringBuilder body = new StringBuilder();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line = reader.readLine()) != null)
                body.append(line);
        } catch (Exception e) { /*report an error*/ }

        return body.toString();
    }
}
