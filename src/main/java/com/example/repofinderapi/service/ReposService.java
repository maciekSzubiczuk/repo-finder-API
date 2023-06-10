package com.example.repofinderapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;

import static com.example.repofinderapi.util.HttpUtil.sendGetRequest;
import static com.example.repofinderapi.util.ResponseUtil.convertResponseToListOfHashMaps;

@Service
public class ReposService {

    private final RestTemplate restTemplate;
    private final String baseUrl;
    private final String token;

    @Autowired
    public ReposService(RestTemplate restTemplate, @Value("${baseurl}") String baseUrl,@Value("${token}") String token) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
        this.token = token;
    }

    public List<HashMap<String, Object>> getReposResponse(String username){
        String url = baseUrl + "/users/"+username+"/repos";
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept","application/vnd.github+json");
        headers.add("Authorization",
                "Bearer "+token);
        headers.add("X-GitHub-Api-Version", "2022-11-28");
        return  convertResponseToListOfHashMaps(sendGetRequest(url,headers));
    }

}
