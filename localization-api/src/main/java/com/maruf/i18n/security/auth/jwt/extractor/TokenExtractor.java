package com.maruf.i18n.security.auth.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
