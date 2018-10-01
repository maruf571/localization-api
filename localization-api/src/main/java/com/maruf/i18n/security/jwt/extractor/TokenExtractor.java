package com.maruf.i18n.security.jwt.extractor;

public interface TokenExtractor {
    String extract(String payload);
}
