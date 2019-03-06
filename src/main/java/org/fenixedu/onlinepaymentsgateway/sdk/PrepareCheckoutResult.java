package org.fenixedu.onlinepaymentsgateway.sdk;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PrepareCheckoutResult {

    @JsonIgnoreProperties(ignoreUnknown=true)
	public static class Result {
		private String code;
		private String description;

		public Result() {
		}

		public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		@Override
		public String toString() {
			return "{code=" + code + ", description=" + description + "}";
		}

	}

	protected Result result;
	protected String buildNumber;
	protected String timestamp;
	protected String ndc;
	protected String id;

	public PrepareCheckoutResult() {
		super();
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNdc() {
		return ndc;
	}

	public void setNdc(String ndc) {
		this.ndc = ndc;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "{result=" + result + ", buildNumber=" + buildNumber + ", timestamp=" + timestamp
				+ ", ndc=" + ndc + ", id=" + id + "}";
	}

}
