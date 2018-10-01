package com.maruf.i18n.security.jwt.extractor;

public interface TokenExtractor {
    public String extract(String payload);
}
