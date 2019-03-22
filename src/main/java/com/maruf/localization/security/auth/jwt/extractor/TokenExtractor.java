package com.maruf.localization.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
