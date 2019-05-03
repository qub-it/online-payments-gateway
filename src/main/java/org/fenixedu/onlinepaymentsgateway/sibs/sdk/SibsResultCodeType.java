package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

public enum SibsResultCodeType {
//declarar o codigo e validar ou não

    SUCCESSFUL_TRANSACTION(true, "^(000[.]000[.]|000[.]100[.]1|000[.][36])"),
    SUCESSFUL_PROCESSED_TRANSACTION_FOR_REVIEW(true, "^(000[.]400[.]0[^3]|000[.]400[.]100)"),
    PENDING_TRANSACTION(true, "^(000[.]200|800[.]400[.]5|100[.]400[.]500)"), CHARGEBACK_RELATED(true, "^(000[.]100[.]2)"),
    REJECTED_TRANSACTION_RISK_CHECK(false, "^(000[.]400[.][1][0-9][1-9]|000[.]400[.]2)"),
    REJECTED_TRANSACTION_EXTERNAL_BANK(false, "^(800[.][17]00|800[.]800[.][123])"),
    REJECTED_COMMUNICATION(false, "^(900[.][1234]00|000[.]400[.]030)"),
    REJECTED_SYSTEM(false, "^(800[.][56]|999[.]|600[.]1|800[.]800[.]8)"), REJECTED_ASYNC_FLOW(false, "^(100[.]39[765][.])"),
    REJECTED_EXTERNAL_RISK(false, "^(100[.]400|100[.]38|100[.]370[.]100|100[.]370[.]11)"),
    REJECTED_RISK_ADDRESS_VALIDATION(false, "^(800[.]400[.]1)"),
    REJECTED_RISK_3DSECURE(false, "^(800[.]400[.]2|100[.]380[.]4|100[.]390)"),
    REJECTED_RISK_BLACKLIST(false, "^(100[.]100[.]701|800[.][32])"), REJECTED_RISK_VALIDATION(false, "^(800[.]1[1-6]0[.])"),
    REJECTED_INVALID_CONFIGURATION(false, "^(600[.][23]|500[.][12]|800[.]121)"),
    REJECTED_INVALID_REGISTRATION(false, "^(100[.][13]50[.])"), REJECTED_INVALID_JOB(false, "^(100[.]250|100[.]360)"),
    REJECTED_INVALID_REFERENCE(false, "^(700[.][1345][05]0[.])"),
    REJECTED_INVALID_FORMAT(false, "^(200[.][123]|100[.][53][07]|800[.]900|100[.][69]00[.]500)"),
    REJECTED_INVALID_ADDRESS(false, "^(100[.]800)"), REJECTED_INVALID_CONTACT(false, "^(100[.][97]00)"),
    REJECTED_INVALID_ACCOUNT(false, "^(100[.]100|100[.]2[01])"), REJECTED_INVALID_AMOUNT(false, "^(100[.]55)"),
    REJECTED_RISK_MANAGEMENT(false, "^(100[.]380[.][23]|100[.]380[.]101)"), INVALID_CODE(false, "");

    private final boolean success;
    private final String regex;

    private SibsResultCodeType(boolean success, String regex) {
        this.success = success;
        this.regex = regex;
    }

    public String getRegex() {
        return regex;
    }

    public boolean isSuccess() {
        return success;
    }
}