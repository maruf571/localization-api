package com.maruf.localization.repository;

import com.maruf.localization.entity.Language;
import com.maruf.localization.entity.Project;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LanguageRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private LanguageRepository languageRepository;


    @Test
    public void shouldFindByProjectId(){

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

        //Pageable pageable = PageRequest.of(1, 20);

        List<Language> languages = languageRepository.findByProjectId(project.getId());

        Assert.assertEquals(2, languages.size());
    }
}
