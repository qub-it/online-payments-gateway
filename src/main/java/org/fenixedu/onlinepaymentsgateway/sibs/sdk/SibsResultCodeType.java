package org.fenixedu.onlinepaymentsgateway.sibs.sdk;

public enum SibsResultCodeType {
    SUCCESSFUL_TRANSACTION(true, "^(000[.]000[.]|000[.]100[.]1|000[.][36])", true),
    SUCESSFUL_PROCESSED_TRANSACTION_FOR_REVIEW(true, "^(000[.]400[.]0[^3]|000[.]400[.]100)", true),
    PENDING_TRANSACTION(true, "^(000[.]200|800[.]400[.]5|100[.]400[.]500)", false),
    CHARGEBACK_RELATED(true, "^(000[.]100[.]2)", true),
    REJECTED_TRANSACTION_RISK_CHECK(false, "^(000[.]400[.][1][0-9][1-9]|000[.]400[.]2)", false),
    REJECTED_TRANSACTION_EXTERNAL_BANK(false, "^(800[.][17]00|800[.]800[.][123])", false),
    REJECTED_COMMUNICATION(false, "^(900[.][1234]00|000[.]400[.]030)", false),
    REJECTED_SYSTEM(false, "^(800[.][56]|999[.]|600[.]1|800[.]800[.]8)", false),
    REJECTED_ASYNC_FLOW(false, "^(100[.]39[765][.])", false),
    REJECTED_EXTERNAL_RISK(false, "^(100[.]400|100[.]38|100[.]370[.]100|100[.]370[.]11)", false),
    REJECTED_RISK_ADDRESS_VALIDATION(false, "^(800[.]400[.]1)", false),
    REJECTED_RISK_3DSECURE(false, "^(800[.]400[.]2|100[.]380[.]4|100[.]390)", false),
    REJECTED_RISK_BLACKLIST(false, "^(100[.]100[.]701|800[.][32])", false),
    REJECTED_RISK_VALIDATION(false, "^(800[.]1[1-6]0[.])", false),
    REJECTED_INVALID_CONFIGURATION(false, "^(600[.][23]|500[.][12]|800[.]121)", false),
    REJECTED_INVALID_REGISTRATION(false, "^(100[.][13]50[.])", false),
    REJECTED_INVALID_JOB(false, "^(100[.]250|100[.]360)", false),
    REJECTED_INVALID_REFERENCE(false, "^(700[.][1345][05]0[.])", false),
    REJECTED_INVALID_FORMAT(false, "^(200[.][123]|100[.][53][07]|800[.]900|100[.][69]00[.]500)", false),
    REJECTED_INVALID_ADDRESS(false, "^(100[.]800)", false), REJECTED_INVALID_CONTACT(false, "^(100[.][97]00)", false),
    REJECTED_INVALID_ACCOUNT(false, "^(100[.]100|100[.]2[01])", false), REJECTED_INVALID_AMOUNT(false, "^(100[.]55)", false),
    REJECTED_RISK_MANAGEMENT(false, "^(100[.]380[.][23]|100[.]380[.]101)", false), INVALID_CODE(false, "", false);

    private final boolean success;
    private final String regex;
    private final boolean paid;

    private SibsResultCodeType(boolean success, String regex, boolean paid) {
        this.success = success;
        this.regex = regex;
        this.paid = paid;
    }

    public String getRegex() {
        return regex;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isPaid() {
        return paid;
    }
}