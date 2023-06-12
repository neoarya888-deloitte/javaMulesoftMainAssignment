package com.mulesoft.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.mule.runtime.extension.api.annotation.param.MediaType;
import static org.mule.runtime.extension.api.annotation.param.MediaType.ANY;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.ParameterGroup;
import org.mule.runtime.extension.api.annotation.param.Config;

public class CurrencyExchangeOperations {

	private static String CURRENCY_API_URL = null;

	@DisplayName("INR to USD")
	@MediaType(value = ANY, strict = false)
	public Double inrToUsd(@Config CurrencyExchangeConfiguration config, @ParameterGroup(name="InputParameters") CurrencyExchangeParameters param) {
		Double result = null;
//		Double amount = 1000.00;
		CURRENCY_API_URL = config.getCurrencyAPIUrl() + "/latest?apikey=" + config.getCurrencyAPIKey();

		try {
			result = param.getAmount() / getExchangeRate("INR");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	private static Double getExchangeRate(String currencyCode) throws IOException {
		URL obj = new URL(CURRENCY_API_URL);
		HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
		connection.setRequestMethod("GET");
		StringBuilder response = new StringBuilder();
		int responseCode = connection.getResponseCode();
		if (responseCode == HttpURLConnection.HTTP_OK) { // success
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
		} else {
			System.out.println("GET Request Failed.");
		}
		String[] arrOfStr = response.toString().replaceAll("\"", "").split(",");

		Double exchangeRate = null;
		for (String a : arrOfStr) {
			if (a.contains(currencyCode))
				exchangeRate = Double.parseDouble(a.split(":")[1]);
		}

		return exchangeRate;
	}
}