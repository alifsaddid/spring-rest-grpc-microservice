package com.services.restservice.controllers;

import com.alifsaddid.clients.restclient.responses.CheckIsEvenResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestApiController {

    @GetMapping("/check")
    public CheckIsEvenResponse checkIsEven(@RequestParam int number) {
        CheckIsEvenResponse response = new CheckIsEvenResponse();
        response.setEven(number % 2 == 0);
        return response;
    }

}
