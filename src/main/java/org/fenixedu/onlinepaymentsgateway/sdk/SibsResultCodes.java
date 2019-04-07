package org.fenixedu.onlinepaymentsgateway.sdk;

public enum SibsResultCodes {
//declarar o codigo e validar ou não

    SUCCESSFUL_TRANSACTION("000.000.000", "Very smooth transaction.", true),
    PENDING_TRANSACTION("000.200.000", "Transaction pending.", true),
    REJECTED_TRANSACTION("100.370.100", "Transaction denied.", false);

    private final String code;
    private final String description;
    private final boolean success;

    private SibsResultCodes(String code, String description, boolean success) {
        this.code = code;
        this.description = description;
        this.success = success;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        return code + ": " + description;
    }

    // Successful = 000.000, 000.100, 000.300, 000.310, 000.600, 000.400.100<
    //Waiting/pending = 100.400,800.400, 000.200
    //Rejected = 000.400.100>=, 800.100, 800.700, 800.800, 000.400.030, 900.100, 
    //900.200, 900.300, 900.400, 600.100, 800.500, 800.600, 800.800, 999.999,
    //100.395-7
    //Reject specific to risk: 100.370, 100.380, 100.400, 800.400, 100.380, 100.390, 800.400,
    //100.100, 800.200, 800.300, 800.310, 800.110, 800.120, 800.130, 800.140, 800.150, 800.160
    //500.100, 500.200, 600.200, 600.300, 800.121, 100.150, 100.350, 100.250, 100.360,
    //700.100, 700.300, 700.400, 700.500, 100.300, 100.370, 100.500, 100.600, 100.900,
    //200.100, 200.200, 200.300, 800.900, 100.800, 100.700, 100.900, 100.100, 100.200,
    //100.210, 100.211, 100.212, 100.550, 100.380
    //Chargeback related: 000.100, 

}