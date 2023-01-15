package com.hoth.client;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;

import java.net.URI;
import java.util.Map;

import static org.springframework.data.repository.init.ResourceReader.Type.JSON;

public class StarwarsCallout {

    public static Map<String, Object> getCalloutResponse(String url){

        var headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON); // NEW AND IMPROVED, SEND US JSON!
        headers.set
        req.setHeader('Content-Type','application/json;charset=UTF-8');
        req.setEndpoint('https://swapi.dev/api/people/1/');
        req.setMethod('GET');
        HTTPResponse res = new Http().send(req);

        if (res.getStatusCode() >= 200 && res.getStatusCode() < 300) {
            Map<String,Object> result = (Map<String,Object>) JSON.deserializeUntyped(res.getBody());
            return result;
        }
        //Throw custom exception
        return null;
    }
}
