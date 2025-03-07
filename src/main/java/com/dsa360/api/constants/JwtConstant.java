package com.dsa360.api.constants;

public enum JwtConstant {
    HEADER_STRING("Authorization"),
    TOKEN_PREFIX("Bearer "),
    SIGNING_KEY("MyHealthifyApplicationSigningKEY702019272609876654321"),
    AUTHORITIES_KEY("scopes"),
    ACCESS_TOKEN_VALIDITY_SECONDS("300000"); // 5 * 60 * 1000 in milliseconds

    private final String value;

    JwtConstant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public int getIntValue() {
        if (this == ACCESS_TOKEN_VALIDITY_SECONDS) {
            return Integer.parseInt(value);
        }
        throw new UnsupportedOperationException("Value is not an integer for this constant.");
    }
}
