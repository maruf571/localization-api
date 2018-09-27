package com.maruf.i18n.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LocalizationKeyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocalizationKeyRepository localizationKeyRepository;

    @Test
    public void dummy(){
        //nothing to test
    }
}
