package main.java.infrastructure;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ExchangeRateApi {
    private static final String API_KEY = "41246728dbb57a954b3e8002";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    public double getExchangeRate(String sourceCurrency, String targetCurrency) {
        try {
            String urlStr = BASE_URL + API_KEY + "/latest/" + sourceCurrency;
            URL url = new URL(urlStr);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Usa Jackson para parsear el JSON
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.toString());
            double exchangeRate = jsonNode.get("conversion_rates").get(targetCurrency).asDouble();

            return exchangeRate;
        } catch (Exception e) {
            e.printStackTrace();
            return -1.0; // Devuelve un valor negativo si hay error
        }
    }
}