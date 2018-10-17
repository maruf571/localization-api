package com.maruf.i18n.security.auth.jwt.verifier;

public interface TokenVerifier {
    boolean verify(String jti);
}
