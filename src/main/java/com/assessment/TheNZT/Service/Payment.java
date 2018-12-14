package com.assessment.TheNZT.Service;



import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import wiremock.org.apache.http.HttpResponse;
import wiremock.org.apache.http.client.methods.CloseableHttpResponse;
import wiremock.org.apache.http.client.methods.HttpGet;
import wiremock.org.apache.http.impl.client.CloseableHttpClient;
import wiremock.org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

@RestController
public class Payment {


    @GetMapping(value="/create")
    public void sendDataByDate(String input) throws IOException {
        String stripe  = "https://api.stripe.com/v1/charges -u sk_test_4eC39HqLyjWDarjtT1zdp7dc: -d amount=2000 -d currency=usd  -d source=tok_amex -d description=Charge for jenny.rosen@example.com";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(stripe);
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String stringResponse = null;
        try {
            stringResponse = convertHttpResponseToString(httpResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String convertHttpResponseToString(HttpResponse httpResponse) throws IOException {
        InputStream inputStream = httpResponse.getEntity().getContent();
        return convertInputStreamToString(inputStream);
    }

    private String convertInputStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream, "UTF-8");
        String string = scanner.useDelimiter("\\Z").next();
        scanner.close();
        return string;
    }


}
