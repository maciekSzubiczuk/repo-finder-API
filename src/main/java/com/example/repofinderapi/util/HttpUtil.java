package com.example.repofinderapi.util;

import org.springframework.http.*;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

public class HttpUtil {

    public static ResponseEntity<String> sendGetRequest(String url, HttpHeaders headers) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public static ResponseEntity<String> sendPostRequest(String url, HttpHeaders headers, MultiValueMap<String, String> requestBody) {
        RestTemplate restTemplate = new RestTemplate();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }

}
