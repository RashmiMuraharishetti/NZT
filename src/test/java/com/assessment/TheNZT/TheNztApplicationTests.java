package com.assessment.TheNZT;

import com.assessment.TheNZT.Service.Payment;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TheNztApplicationTests {

	@Test
	public void contextLoads() {
	}

    @Rule
    public WireMockRule wireMockRule = new WireMockRule();

	@Test
    public void testWireMock(){
        Payment payment = new Payment();
        wireMockRule.stubFor(get(urlEqualTo("https://api.stripe.com/v1/charges -u sk_test_4eC39HqLyjWDarjtT1zdp7dc: -d amount=2000 -d currency=usd  -d source=tok_amex -d description=Charge for jenny.rosen@example.com"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("\"testing-library\": \"WireMock\"")));

    }

}





