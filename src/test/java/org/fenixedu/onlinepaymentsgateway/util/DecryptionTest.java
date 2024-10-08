package org.fenixedu.onlinepaymentsgateway.util;

import org.junit.jupiter.api.Test;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.junit.jupiter.api.Assertions.*;

class DecryptionTest {

    @Test
    void testCipherExists() throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException {
        assertDoesNotThrow(Decryption::getCipherInstance);
        assertNotNull(Decryption.getCipherInstance());
    }

}