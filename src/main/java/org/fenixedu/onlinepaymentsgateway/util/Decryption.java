package org.fenixedu.onlinepaymentsgateway.util;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Decryption {

    public static final int GCM_TAG_LENGTH = 16; // in bytes

    // X-Initialization-Vector Header
    private String iv;
    
    // AES KEY
    private String aesKey;
    
    // X-Authentication-Tag Header
    private String authTag;
    
    private String payload;

    public Decryption(String aesKey, String iv, String authTag, String payload) {
        super();
        this.iv = iv;
        this.aesKey = aesKey;
        this.authTag = authTag;
        this.payload = payload;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public String getKey() {
        return aesKey;
    }

    public void setKey(String aesKey) {
        this.aesKey = aesKey;
    }

    public String getAuthTag() {
        return authTag;
    }

    public void setAuthTag(String authTag) {
        this.authTag = authTag;
    }

    public String getCipherText() {
        return payload;
    }

    public void setCipherText(String cipherText) {
        this.payload = cipherText;
    }

    public static int getGcmTagLength() {
        return GCM_TAG_LENGTH;
    }

    public String decryptPayload() throws Exception {
        final byte[] bKey = Hex.decodeHex(aesKey.toCharArray());
        final byte[] bIV = Hex.decodeHex(iv.toCharArray());
        final byte[] encryptedBytes = Hex.decodeHex((payload + authTag).toCharArray());

        final SecretKeySpec sKey = new SecretKeySpec(bKey, "AES");
        final Cipher cipher = getCipherInstance();
        final GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, bIV);
        cipher.init(Cipher.DECRYPT_MODE, sKey, spec);

        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    protected static Cipher getCipherInstance() throws NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException {
        return Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
    }

}