package com.maruf.i18n.security.jwt.verifier;

public interface TokenVerifier {
    boolean verify(String jti);
}
