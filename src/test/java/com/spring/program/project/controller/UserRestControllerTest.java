package com.spring.program.project.controller;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

@ContextConfiguration
public class UserRestControllerTest {


    @Test
    public void givenUsers_whenUnauthorizedUserTryToGetUsers_then403IsReceived()
            throws ClientProtocolException, IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080//users");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void givenUserById_whenUnauthorizedUserTryToGetUserById_then403IsReceived()
            throws ClientProtocolException, IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080//users/1");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_FORBIDDEN);
    }
}
