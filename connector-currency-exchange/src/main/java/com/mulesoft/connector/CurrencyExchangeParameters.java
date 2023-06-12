package com.mulesoft.connector;

import org.mule.runtime.api.meta.ExpressionSupport;
import org.mule.runtime.extension.api.annotation.Expression;
import org.mule.runtime.extension.api.annotation.param.Parameter;
import org.mule.runtime.extension.api.annotation.param.display.DisplayName;


public class CurrencyExchangeParameters {
	@Parameter
	@DisplayName("Amount")
	@Expression(ExpressionSupport.SUPPORTED)
	private Double amount;

	public Double getAmount() {
		return amount;
	}
}
