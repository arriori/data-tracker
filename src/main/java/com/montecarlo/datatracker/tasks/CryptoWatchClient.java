package com.montecarlo.datatracker.tasks;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.stereotype.Component;

@Component
public class CryptoWatchClient {

    // TODO: Move to config.
    private String Url = "https://api.cryptowat.ch/markets/kraken/%s/price";

    public double getLatestCurrencyValue(String code) {
        String finalUrl = String.format(Url, code);

        try {
            HttpRequest request = HttpRequest.newBuilder().uri(new URI(finalUrl)).GET().build();
            HttpClient client = HttpClient.newBuilder().build();

            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseString = response.body();

                JsonObject jsonObject = JsonParser.parseString(responseString).getAsJsonObject();
                JsonObject result = jsonObject.getAsJsonObject("result");
                double resultLong = result.get("price").getAsDouble();
                return resultLong;
            } 

        } catch (Exception e) {
            //TODO: handle exception
        }
    
        return 0;
    }
    
}
