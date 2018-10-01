package com.maruf.i18n.repository;

import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.localization.entity.LocalizationKey;
import com.maruf.i18n.localization.entity.LocalizationValue;
import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.localization.repository.LocalizationValueRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@DataJpaTest
@RunWith(SpringRunner.class)
public class LocalizationKeyValueRepositoryTest {

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

        LocalizationKey localizationKey = new LocalizationKey();
        localizationKey.setLangKey("user.name");
        localizationKey.setProject(project);
        entityManager.persist(localizationKey);


        LocalizationValue localizationValue = new LocalizationValue();
        localizationValue.setValue("User name");
        localizationValue.setLanguage(language1);
        localizationValue.setLocalizationKey(localizationKey);
        entityManager.persist(localizationValue);

        LocalizationValue found = localizationValueRepository.findByLanguageAndLocalizationKey(language1, localizationKey);
        Assert.assertEquals("User name", found.getValue());

    }
}
