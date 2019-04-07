package org.fenixedu.onlinepaymentsgateway.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PaymentFormBean {

    private String htmlScript;
    private String htmlForm;

    public PaymentFormBean(String htmlScript, String htmlForm) {
        super();
        this.htmlScript = htmlScript;
        this.htmlForm = htmlForm;
    }

    public PaymentFormBean() {
    }

    public String getHtmlScript() {
        return htmlScript;
    }

    public void setHtmlScript(String htmlScript) {
        this.htmlScript = htmlScript;
    }

    public String getHtmlForm() {
        return htmlForm;
    }

    public void setHtmlForm(String htmlForm) {
        this.htmlForm = htmlForm;
    }

    @Override
    public String toString() {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
