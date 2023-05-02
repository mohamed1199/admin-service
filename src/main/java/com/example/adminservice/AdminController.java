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
import java.util.HashMap;
import java.util.Map;

@Component
@RestController
@RequestMapping("/api/admin-service")
public class AdminController {

    @Value("${USER_SERVICE_ENDPOINT}")
    private String userServiceEndpoint ;

    @Value("${DATABASE_ENDPOINT}")
    private String databaseEndpoint ;

    @Value("${KAFKA_ENDPOINT}")
    private String kafkaEndpoint ;

    @GetMapping("/hello")
    public String getDefault(){
        return "Hello from Admin Service - V1.0.0.0";
    }

    @GetMapping("/admins")
    public String getHello(){
        return "I have 5 admins";
    }

    @GetMapping("/envs")
    public String getEnvVars(){
        Map<String, String> myMap = new HashMap<>();
        myMap.put("USER_SERVICE_ENDPOINT",userServiceEndpoint.toString());
        myMap.put("DATABASE_ENDPOINT",databaseEndpoint.toString());
        myMap.put("KAFKA_ENDPOINT",kafkaEndpoint.toString());
        return myMap.toString() ;
    }

    @GetMapping("/connect")
    public String getConnect(){
        RestTemplate restTemplate = new RestTemplate();
        String uri = "http://"+ userServiceEndpoint + "/api/user-service/hello";
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<?> result =
                restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
        return (String)  "Response from user-service : "+result.getBody().toString() ;
    }


}
