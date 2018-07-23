package com.maruf.localization.repository;
import java.util.ArrayList;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Localization;
import com.maruf.localization.entity.LocalizationValue;
import com.maruf.localization.entity.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LocalizationValueRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LocalizationValueRepository localizationValueRepository;

    @Test
    public void shouldFindByLanguageAndLocalization(){

        Project project = new Project();
        project.setName("project1");
        project.setUrl("a.project.com");
        entityManager.persist(project);


        Language language = new Language();
        language.setName("Bangla");
        language.setCode("bn");
        language.setIsRtl(false);
        language.setIsActive(true);
        language.setProject(project);
        entityManager.persist(language);

        Language language1 = new Language();
        language1.setName("English");
        language1.setCode("en");
        language1.setIsRtl(false);
        language1.setIsActive(true);
        language1.setProject(project);
        entityManager.persist(language1);

        Localization localization = new Localization();
        localization.setLangKey("user.name");
        localization.setProject(project);
        entityManager.persist(localization);


        LocalizationValue localizationValue = new LocalizationValue();
        localizationValue.setValue("User name");
        localizationValue.setLanguage(language1);
        localizationValue.setLocalization(localization);
        entityManager.persist(localizationValue);

        LocalizationValue found = localizationValueRepository.findByLanguageAndLocalization(language1, localization);
        Assert.assertEquals("User name", found.getValue());

    }
}
