/*
 * 
 */
package org.fenixedu.demo.client;

import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

// TODO: Auto-generated Javadoc
/**
 * The Class Authentication.
 */
public class Authentication {

	/** The user id. */
	private final String userId;

	/** The password. */
	private final String password;

	/** The entity id. */
	private final String entityId;

	/**
	 * Instantiates a new authentication.
	 *
	 * @param userId   the user id
	 * @param password the password
	 * @param entityId the entity id
	 */
	public Authentication(String userId, String password, String entityId) {
		super();
		this.userId = userId;
		this.password = password;
		this.entityId = entityId;
	}

	/**
	 * As map.
	 *
	 * @return the multivalued map
	 */
	public MultivaluedMap<String, String> asMap() {
		MultivaluedMap<String, String> form = new MultivaluedHashMap<>();
		form.add("authentication.userId", userId);
		form.add("authentication.password", password);
		form.add("authentication.entityId", entityId);
		return form;
	}
}