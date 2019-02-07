package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.core.MultivaluedMap;

public class PrepareMBCheckout {

	Authentication authentication;
	PrepareCheckout prepareCheckout;
	PaymentBrand paymentBrand;
	String merchantTransactionId;
	String SIBSMULTIBANCO_PtmntEntty;
	String SIBSMULTIBANCO_RefIntlDtTm;
	String SIBSMULTIBANCO_RefLmtDtTm;
	String ip;
	String surname;
	String givenName;
	String country;
	String shopperResultUrl;

	public PrepareMBCheckout(Authentication authentication, PrepareCheckout prepareCheckout, String paymentBrand,
			String merchantTransactionId, String sIBSMULTIBANCO_PtmntEntty, String sIBSMULTIBANCO_RefIntlDtTm,
			String sIBSMULTIBANCO_RefLmtDtTm, String ip, String surname, String givenName, String country,
			String shopperResultUrl) {
		super();
		this.authentication = authentication;
		this.prepareCheckout = prepareCheckout;
		this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
		this.merchantTransactionId = merchantTransactionId;
		this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
		this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
		this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
		this.ip = ip;
		this.surname = surname;
		this.givenName = givenName;
		this.country = country;
		this.shopperResultUrl = shopperResultUrl;
	}

	public Authentication getAuthentication() {
		return authentication;
	}

	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

	public PrepareCheckout getPrepareCheckout() {
		return prepareCheckout;
	}

	public void setPrepareCheckout(PrepareCheckout prepareCheckout) {
		this.prepareCheckout = prepareCheckout;
	}

	public PaymentBrand getPaymentBrand() {
		return paymentBrand;
	}

	public void setPaymentBrand(String paymentBrand) {
		this.paymentBrand = PaymentBrand.valueOf(paymentBrand);
	}

	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}

	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}

	public String getSIBSMULTIBANCO_PtmntEntty() {
		return SIBSMULTIBANCO_PtmntEntty;
	}

	public void setSIBSMULTIBANCO_PtmntEntty(String sIBSMULTIBANCO_PtmntEntty) {
		this.SIBSMULTIBANCO_PtmntEntty = sIBSMULTIBANCO_PtmntEntty;
	}

	public String getSIBSMULTIBANCO_RefIntlDtTm() {
		return SIBSMULTIBANCO_RefIntlDtTm;
	}

	public void setSIBSMULTIBANCO_RefIntlDtTm(String sIBSMULTIBANCO_RefIntlDtTm) {
		this.SIBSMULTIBANCO_RefIntlDtTm = sIBSMULTIBANCO_RefIntlDtTm;
	}

	public String getSIBSMULTIBANCO_RefLmtDtTm() {
		return SIBSMULTIBANCO_RefLmtDtTm;
	}

	public void setSIBSMULTIBANCO_RefLmtDtTm(String sIBSMULTIBANCO_RefLmtDtTm) {
		this.SIBSMULTIBANCO_RefLmtDtTm = sIBSMULTIBANCO_RefLmtDtTm;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getGivenName() {
		return givenName;
	}

	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getShopperResultUrl() {
		return shopperResultUrl;
	}

	public void setShopperResultUrl(String shopperResultUrl) {
		this.shopperResultUrl = shopperResultUrl;
	}

	public MultivaluedMap<String, String> asMap() {
		MultivaluedMap<String, String> form = prepareCheckout.asMap();
		form.add("paymentBrand", paymentBrand.toString());
		form.add("merchantTransactionId", merchantTransactionId);
		form.add("customParameters[SIBSMULTIBANCO_PtmntEntty]", SIBSMULTIBANCO_PtmntEntty);
		form.add("customParameters[SIBSMULTIBANCO_RefIntlDtTm]", SIBSMULTIBANCO_RefIntlDtTm);
		form.add("customParameters[SIBSMULTIBANCO_RefLmtDtTm]", SIBSMULTIBANCO_RefLmtDtTm);
		form.add("customer.ip", ip);
		form.add("customer.surname", surname);
		form.add("customer.givenName", givenName);
		form.add("billing.country", country);
		form.add("shopperResultUrl", shopperResultUrl);
		return form;
	}
}
