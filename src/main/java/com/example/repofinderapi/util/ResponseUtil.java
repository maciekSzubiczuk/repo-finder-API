package com.example.repofinderapi.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.List;

public class ResponseUtil {

    public static List<HashMap<String, Object>> convertResponseToListOfHashMaps(ResponseEntity<String> responseEntity) {
        String responseBody = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<HashMap<String, Object>>> typeRef = new TypeReference<>() {
        };
        try {
            return objectMapper.readValue(responseBody, typeRef);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
