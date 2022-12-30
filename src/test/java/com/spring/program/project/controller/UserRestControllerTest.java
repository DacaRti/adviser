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
    public void givenUsers_whenUserTryToGetUsers_then403IsReceived()
            throws ClientProtocolException, IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080//users/");
        request.addHeader("Authorization", TestData.userJwtToken);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_FORBIDDEN);
    }

    @Test
    public void givenUserById_whenUserTryToGetUserById_then403IsReceived()
            throws ClientProtocolException, IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080//users/1");
        request.addHeader("Authorization", TestData.userJwtToken);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(),
                HttpStatus.SC_FORBIDDEN);
    }

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

    interface TestData {
        String adminJwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTY3MjA4MjE5MSwiZXhwIjoxNjcyMTY4NTkxfQ.IkALFw90BsrRTdspDqh88rRC3_YKE03JJcFa_ZBz3ycabJNoDGnIRl5Cy9KZUaWLsy3m8n-OsprTiaWLZ5lxNw";
        String userJwtToken = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiaWF0IjoxNjcyMDgyMTA2LCJleHAiOjE2NzIxNjg1MDZ9.0IZvfuaS-eua_OrqWHPD1MEgcnNcl720n5NuN3ShjVgzIZq0nNA4hjEQFyoYBX0IBcdlVoKRO1Xy8-KomvS_KQ";
    }
}
