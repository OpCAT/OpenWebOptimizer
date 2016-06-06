package com.opcat.util;

/**
 * Possible Cache-Control directives for cacheable responses.
 * 
 * @author OpCAT
 * @07-May-2013 com.opcat.util
 */
public enum CacheAbility {
	/**
	 * Indicates that the response MAY be cached by any cache, even if it would
	 * normally be non-cacheable or cacheable only within a non-shared cache.
	 */
	PUBLIC("public"),
	/**
	 * Indicates that all or part of the response message is intended for a
	 * single user and MUST NOT be cached by a shared cache. This allows an
	 * origin server to state that the specified parts of the response are
	 * intended for only one user and are not a valid response for requests by
	 * other users. A private (non-shared) cache MAY cache the response.
	 */
	PRIVATE("private");

	private String value;

	CacheAbility(String value) {
		this.value = value;
	}

	/**
	 * Gets the Cache-Control directive value.
	 * 
	 * @return the Cache-Control directive value
	 */
	public String getValue() {
		return this.value;
	}
}
