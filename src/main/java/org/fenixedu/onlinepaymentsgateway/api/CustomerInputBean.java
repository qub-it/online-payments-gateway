package org.fenixedu.onlinepaymentsgateway.api;

public class CustomerInputBean {

    private String customerIp;
    private String customerSurname;
    private String customerGivenName;
    private String customerCountry;

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

    public String getCountry() {
        return customerCountry;
    }

    public void setCountry(String country) {
        this.customerCountry = country;
    }

}
