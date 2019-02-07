package org.fenixedu.onlinepaymentsgateway.sdk;

/**
 * @author racarvalho
 *
 */
public class PrepareMBCheckoutResult extends PrepareCheckoutResult {
	public PrepareMBCheckoutResult() {
		super();
	}

	public static class ResultDetails {

		private String ExtendedDescription;
		private String refLmtDtTm;
		private String amount;
		private String ptmntEntty;
		private String ConnectorTxID1;
		private String ConnectorTxID3;
		private String ConnectorTxID2;
		private String pmtRef;
		private String acquirerResponse;
		private String uuid;
		private String pmtRefNtty;

		public ResultDetails() {
		}

		public String getExtendedDescription() {
			return ExtendedDescription;
		}

		public void setExtendedDescription(String extendedDescription) {
			this.ExtendedDescription = extendedDescription;
		}

		public String getRefLmtDtTm() {
			return refLmtDtTm;
		}

		public void setRefLmtDtTm(String refLmtDtTm) {
			this.refLmtDtTm = refLmtDtTm;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		public String getPtmntEntty() {
			return ptmntEntty;
		}

		public void setPtmntEntty(String ptmntEntty) {
			this.ptmntEntty = ptmntEntty;
		}

		public String getConnectorTxID1() {
			return ConnectorTxID1;
		}

		public void setConnectorTxID1(String connectorTxID1) {
			this.ConnectorTxID1 = connectorTxID1;
		}

		public String getConnectorTxID3() {
			return ConnectorTxID3;
		}

		public void setConnectorTxID3(String connectorTxID3) {
			this.ConnectorTxID3 = connectorTxID3;
		}

		public String getConnectorTxID2() {
			return ConnectorTxID2;
		}

		public void setConnectorTxID2(String connectorTxID2) {
			this.ConnectorTxID2 = connectorTxID2;
		}

		public String getPmtRef() {
			return pmtRef;
		}

		public void setPmtRef(String pmtRef) {
			this.pmtRef = pmtRef;
		}

		public String getAcquirerResponse() {
			return acquirerResponse;
		}

		public void setAcquirerResponse(String acquirerResponse) {
			this.acquirerResponse = acquirerResponse;
		}

		public String getUuid() {
			return uuid;
		}

		public void setUuid(String uuid) {
			this.uuid = uuid;
		}

		public String getPmtRefNtty() {
			return pmtRefNtty;
		}

		public void setPmtRefNtty(String pmtRefNtty) {
			this.pmtRefNtty = pmtRefNtty;
		}

		@Override
		public String toString() {
			return "ResultDetails [ExtendedDescription=" + ExtendedDescription + ", refLmtDtTm=" + refLmtDtTm
					+ ", amount=" + amount + ", ptmntEntty=" + ptmntEntty + ", ConnectorTxID1=" + ConnectorTxID1
					+ ", ConnectorTxID3=" + ConnectorTxID3 + ", ConnectorTxID2=" + ConnectorTxID2 + ", pmtRef=" + pmtRef
					+ ", AcquirerResponse=" + acquirerResponse + ", uuid=" + uuid + ", pmtRefNtty=" + pmtRefNtty + "]";
		}
	}

	public static class Customer {
		private String givenName;
		private String surname;
		private String ip;

		public Customer() {
		}

		public String getGivenName() {
			return givenName;
		}

		public void setGivenName(String givenName) {
			this.givenName = givenName;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getIp() {
			return ip;
		}

		public void setIp(String ip) {
			this.ip = ip;
		}

		@Override
		public String toString() {
			return "Customer [givenName=" + givenName + ", surname=" + surname + ", ip=" + ip + "]";
		}

	}

	public static class CustomParameters {
		private String SIBSMULTIBANCO_PtmntEntty;
		private String SIBSMULTIBANCO_RefIntlDtTm;
		private String SIBSMULTIBANCO_RefLmtDtTm;

		public CustomParameters() {
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

		@Override
		public String toString() {
			return "customParameters [SIBSMULTIBANCO_PtmntEntty=" + SIBSMULTIBANCO_PtmntEntty
					+ ", SIBSMULTIBANCO_RefIntlDtTm=" + SIBSMULTIBANCO_RefIntlDtTm + ", SIBSMULTIBANCO_RefLmtDtTm="
					+ SIBSMULTIBANCO_RefLmtDtTm + "]";
		}

	}

	public static class Billing {
		private String country;

		public Billing() {
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		@Override
		public String toString() {
			return "Billing [country=" + country + "]";
		}

	}

	private ResultDetails resultDetails;
	private Customer customer;
	private CustomParameters customParameters;
	private Billing billing;
	private String paymentType;
	private String paymentBrand;
	private String amount;
	private String currency;
	private String descriptor;
	private String merchantTransactionId;

	public ResultDetails getResultDetails() {
		return resultDetails;
	}

	public void setResultDetails(ResultDetails resultDetails) {
		this.resultDetails = resultDetails;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CustomParameters getCustomParameters() {
		return customParameters;
	}

	public void setCustomParameters(CustomParameters customParameters) {
		this.customParameters = customParameters;
	}

	public Billing getBilling() {
		return billing;
	}

	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentBrand() {
		return paymentBrand;
	}

	public void setPaymentBrand(String paymentBrand) {
		this.paymentBrand = paymentBrand;
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

	public String getDescriptor() {
		return descriptor;
	}

	public void setDescriptor(String descriptor) {
		this.descriptor = descriptor;
	}

	public String getMerchantTransactionId() {
		return merchantTransactionId;
	}

	public void setMerchantTransactionId(String merchantTransactionId) {
		this.merchantTransactionId = merchantTransactionId;
	}

	@Override
	public String toString() {
		return "PrepareMBCheckoutResult [id=" + id + ", paymentType=" + paymentType + ", paymentBrand=" + paymentBrand
				+ ", amount=" + amount + ", currency=" + currency + ", descriptor=" + descriptor
				+ ", merchantTransactionId=" + merchantTransactionId + ", result=" + result + ", resultDetails="
				+ resultDetails + ", customer=" + customer + ", billing=" + billing + ", customParameters="
				+ customParameters + ", buildNumber=" + buildNumber + ", timestamp=" + timestamp + ", ndc=" + ndc + "]";
	}

}