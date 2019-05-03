package org.fenixedu.onlinepaymentsgateway.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerDataInputBean {

    private String customerIp;
    private String customerSurname;
    private String customerGivenName;

    public CustomerDataInputBean(String customerIp, String customerSurname, String customerGivenName) {
        super();
        this.customerIp = customerIp;
        this.customerSurname = customerSurname;
        this.customerGivenName = customerGivenName;
    }

    public CustomerDataInputBean() {
    }

    public String getIp() {
        return customerIp;
    }

    public void setIp(String ip) {
        this.customerIp = ip;
    }

    public String getSurname() {
        return customerSurname;
    }

    public void setSurname(String surname) {
        this.customerSurname = surname;
    }

    public String getGivenName() {
        return customerGivenName;
    }

    public void setGivenName(String givenName) {
        this.customerGivenName = givenName;
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
