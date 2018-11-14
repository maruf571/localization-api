package com.maruf.i18n.language;

import com.maruf.i18n.AbstractTest;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

public class LanguageApiTest extends AbstractTest {


    @Test
    @Transactional
    public void shouldCreateLanguage(){
        System.out.println("Hi");
    }
}
