package com.mulesoft.connector;

import org.mule.runtime.extension.api.annotation.Operations;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;
import org.mule.runtime.extension.api.annotation.param.display.Example;
import org.mule.runtime.extension.api.annotation.param.display.Placement;

@Operations(CurrencyExchangeOperations.class)
public class CurrencyExchangeConfiguration {
	
	@Parameter
	@DisplayName("Currency API URL")
	@Placement(order = 1)
	@Example("https://api.currencyfreaks.com")
	private String currencyAPIUrl;
	
	@Parameter
	@DisplayName("Currency API Key")
	@Placement(order = 2)
	@Example("3579bb9476be49dab8c3aeed152ed428")	
	private String currencyAPIKey;
	
	public String getCurrencyAPIUrl() {
		return currencyAPIUrl;
	}
	
	public String getCurrencyAPIKey() {
		return currencyAPIKey;
	}
}