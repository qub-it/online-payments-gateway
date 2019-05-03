package org.fenixedu.onlinepaymentsgateway.exceptions;

public class OnlinePaymentsGatewayCommunicationException extends Exception {

    private static final long serialVersionUID = -5752548514536825487L;
    private String requestLog;
    private String responseLog;

    public OnlinePaymentsGatewayCommunicationException(String requestLog, String responseLog) {
        super();
        this.requestLog = requestLog;
        this.responseLog = responseLog;
    }

    public OnlinePaymentsGatewayCommunicationException(String requestLog, String responseLog, String message, Throwable cause) {
        super(message, cause);
        this.requestLog = requestLog;
        this.responseLog = responseLog;
    }

    public OnlinePaymentsGatewayCommunicationException(String requestLog, String responseLog, String message) {
        super(message);
        this.requestLog = requestLog;
        this.responseLog = responseLog;
    }

    public OnlinePaymentsGatewayCommunicationException(String requestLog, String responseLog, Throwable cause) {
        super(cause);
        this.requestLog = requestLog;
        this.responseLog = responseLog;
    }

    public String getRequestLog() {
        return requestLog;
    }

    public void setRequestLog(String requestLog) {
        this.requestLog = requestLog;
    }

    public String getResponseLog() {
        return responseLog;
    }

    public void setResponseLog(String responseLog) {
        this.responseLog = responseLog;
    }

}
