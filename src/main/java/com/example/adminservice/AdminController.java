package com.example.adminservice;

import org.apache.juli.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Collections;
import java.util.Map;

@Component
@RestController
@RequestMapping("/api/admin-service")
public class AdminController {

    @Value("${USER_SERVICE_ENDPOINT}")
    private String userServiceEndpoint ;

    @GetMapping("/")
    public String getDefault(){
        return "Hello from Admin Service - ";
    }


    @GetMapping("/admins")
    public String getHello(){
        return "I have 5 admins - ";
    }

    @GetMapping("/env")
    public String getEnvVars(){
        return "user service endpoint is " + userServiceEndpoint ;
    }

    @GetMapping("/connect")
    public String connect(){
        RestTemplate restTemplate = new RestTemplate();

        String uri = "http://"+ userServiceEndpoint + "/api/user-service/";

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return (String)  "Response from User service : " + result.getBody() ;
    }


}
