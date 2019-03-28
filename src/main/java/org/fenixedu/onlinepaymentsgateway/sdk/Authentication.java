/*
 * No longer used, updated authentication with HTTP auth token
 */

package org.fenixedu.onlinepaymentsgateway.sdk;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

public class Authentication {

    private final String userId;

    private final String password;

    private final String entityId;

    public Authentication(String userId, String password, String entityId) {
        super();
        this.userId = userId;
        this.password = password;
        this.entityId = entityId;
    }

    public MultivaluedMap<String, String> asMap() {
        MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
        form.add("authentication.userId", userId);
        form.add("authentication.password", password);
        form.add("authentication.entityId", entityId);
        return form;
    }
}