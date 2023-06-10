package com.example.repofinderapi.controller;

import com.example.repofinderapi.error.ErrorResponse;
import com.example.repofinderapi.service.RepoFinderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.RepoFinderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/repo-finder")
public class RepoFinderController {

    @Value("${error.message.XMLMediaType}")
    private String xmlHeaderErrorMessage;
    @Value("${error.code.XMLMediaType}")
    private int xmlHeaderErrorCode;
    @Value("${error.message.notFound}")
    private String notFoundErrorMessage;
    @Value("${error.code.notFound}")
    private int notFoundErrorCode;
    @Value("${error.message.invalidMediaType}")
    private String invalidMediaTypeErrorMessage;
    @Value("${error.code.invalidMediaType}")
    private int invalidMediaTypeErrorCode;

    private final RepoFinderService repoFinderService;

    @Autowired
    public RepoFinderController(RepoFinderService repoFinderService) {
        this.repoFinderService = repoFinderService;
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<?> getUserRepos(@PathVariable String username, @RequestHeader HttpHeaders headers) throws JsonProcessingException {
        // checking for application/xml header
        if (headers.getAccept().contains(MediaType.APPLICATION_XML)) {
            ErrorResponse errorResponse = new ErrorResponse(xmlHeaderErrorCode, xmlHeaderErrorMessage);
            return ResponseEntity
                    .status(HttpStatus.NOT_ACCEPTABLE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
        if (!headers.getAccept().contains(MediaType.APPLICATION_JSON)) {
            ErrorResponse errorResponse = new ErrorResponse(invalidMediaTypeErrorCode, invalidMediaTypeErrorMessage);
            return ResponseEntity
                    .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(errorResponse);
        }
        RepoFinderResponse response = new RepoFinderResponse();
        // catching 404 exception is given user doesn't exist
        try {
            response = repoFinderService.populateRepoFinderResponse(username);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                ErrorResponse errorResponse = new ErrorResponse(notFoundErrorCode, notFoundErrorMessage);
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(errorResponse);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.writeValueAsString(response));
    }

}
