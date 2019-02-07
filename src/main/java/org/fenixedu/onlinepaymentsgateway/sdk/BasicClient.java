package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;

public class BasicClient {

	Client client = ClientBuilder.newClient();
	private final WebTarget base;

	public BasicClient(String url) {
		this.base = client.target(url);
	}

	public String answer(String param1, String param2) {
		// http://localhost:3000/answer?param1=try&param2=it
		// Payload entity = new Payload(param1, param2);
		WebTarget answer = base.path("answer").queryParam("param1", param1).queryParam("param2", param2);
		return answer.request(MediaType.TEXT_PLAIN).get(String.class);
	}

	public String prepareCheckout(String userId, String password, String entityId, String amount, String currency,
			String paymentType) {
		WebTarget postit = base.path("checkouts");
		Authentication auth = new Authentication(userId, password, entityId);
		PrepareCheckout prepCheckout = new PrepareCheckout(auth, amount, currency, paymentType);
		Form form = new Form(prepCheckout.asMap());
		PrepareCheckoutResult json = postit.request("application/x-www-form-urlencoded; charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareCheckoutResult.class);
		return json.toString();
	}

	public String mbPrepareCheckout(String userId, String password, String entityId, String amount, String currency,
			String paymentType, String paymentBrand, String merchantTransactionId, String sIBSMULTIBANCO_PtmntEntty,
			String sIBSMULTIBANCO_RefIntlDtTm, String sIBSMULTIBANCO_RefLmtDtTm, String ip, String surname,
			String givenName, String country, String shopperResultUrl) {
		WebTarget postit = base.path("payments");
		org.fenixedu.onlinepaymentsgateway.sdk.Authentication auth = new Authentication(userId, password, entityId);
		PrepareCheckout prepCheckout = new PrepareCheckout(auth, amount, currency, paymentType);
		PrepareMBCheckout mbPrepCheckout = new PrepareMBCheckout(auth, prepCheckout, paymentBrand,
				merchantTransactionId, sIBSMULTIBANCO_PtmntEntty, sIBSMULTIBANCO_RefIntlDtTm, sIBSMULTIBANCO_RefLmtDtTm,
				ip, surname, givenName, country, shopperResultUrl);
		Form form = new Form(mbPrepCheckout.asMap());

		PrepareMBCheckoutResult json = postit.request("application/x-www-form-urlencoded; charset=UTF-8")
				.accept(MediaType.APPLICATION_JSON).post(Entity.form(form), PrepareMBCheckoutResult.class);
		return json.toString();
	}

	public void closeClient() {
		client.close();
	}

}