package com.maruf.localization.security.auth.jwt.verifier;

public interface TokenVerifier {
    boolean verify(String jti);
}
