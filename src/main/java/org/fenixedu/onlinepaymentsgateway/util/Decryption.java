package org.fenixedu.onlinepaymentsgateway.util;

import java.nio.charset.StandardCharsets;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;

public class Decryption {

    public static final int GCM_TAG_LENGTH = 16; // in bytes

    private String iv = "<X-Initialization-Vector Header>";
    private String aesKey = "<AES KEY>";
    private String authTag = "<X-Authentication-Tag Header>";
    private String payload = "<payload body>";

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
        byte[] bKey = Hex.decodeHex(aesKey.toCharArray());
        byte[] bIV = Hex.decodeHex(iv.toCharArray());
        byte[] encryptedBytes = Hex.decodeHex((payload + authTag).toCharArray());

        SecretKeySpec sKey = new SecretKeySpec(bKey, "AES");
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding", "SunJCE");
        GCMParameterSpec spec = new GCMParameterSpec(GCM_TAG_LENGTH * 8, bIV);
        cipher.init(Cipher.DECRYPT_MODE, sKey, spec);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }

    /*TODO public MBNotificationBean handleMBNotification(NotificationBean notificationBean) {
        return null
    }*/

}