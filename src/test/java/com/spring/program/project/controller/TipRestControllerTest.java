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
public class TipRestControllerTest {

        @Test
        public void givenAllTips_whenUnauthorizedUserTryToGetTips_then403IsReceived()
                        throws ClientProtocolException, IOException {
                // Given
                HttpUriRequest request = new HttpGet("http://localhost:8080//tips/");

                // When
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

                // Then
                Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
        }

        @Test
        public void givenTipById_whenUnauthorizedUserTryToGetTips_then403IsReceived()
                        throws ClientProtocolException, IOException {
                // Given
                HttpUriRequest request = new HttpGet("http://localhost:8080//tip/1");

                // When
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

                // Then
                Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
        }

        @Test
        public void givenRandomTip_whenUnauthorizedUserTryToGetTips_then403IsReceived()
                        throws ClientProtocolException, IOException {
                // Given
                HttpUriRequest request = new HttpGet("http://localhost:8080//tip/random");

                // When
                HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

                // Then
                Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), HttpStatus.SC_FORBIDDEN);
        }
}