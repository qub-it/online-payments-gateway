package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.core.MultivaluedMap;

public class PrepareCheckout {
	Authentication authentication;
	String amount;
	String currency;
	PaymentType paymentType;

	public PrepareCheckout(Authentication authentication, String amount, String currency, String paymentType) {
		super();
		this.authentication = authentication;
		this.amount = amount;
		this.currency = currency;
		this.paymentType = PaymentType.valueOf(paymentType);
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = PaymentType.valueOf(paymentType);
	}

	public MultivaluedMap<String, String> asMap() {
		MultivaluedMap<String, String> form = authentication.asMap();
		form.add("amount", amount);
		form.add("currency", currency);
		form.add("paymentType", paymentType.toString());
		return form;
	}

}
