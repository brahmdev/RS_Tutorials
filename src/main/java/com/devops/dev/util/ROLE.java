/**
 * 
 */
package com.devops.dev.util;

/**
 * @author KalpDev
 *
 */
public enum ROLE {
	ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_STUDENT("ROLE_STUDENT")
    ;

    private final String text;

    /**
     * @param text
     */
    private ROLE(final String text) {
        this.text = text;
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return text;
    }
}
