package com.maruf.i18n.localization;

import com.maruf.i18n.AbstractTest;
import com.maruf.i18n.language.LanguageApiTest;
import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.localization.dao.LocalizationDao;
import com.maruf.i18n.localization.dto.LocalizationDto;
import com.maruf.i18n.project.ProjectApiTest;
import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.security.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class LocalizationApiTest extends AbstractTest {

    private Project project;

    private Language language;

    private LocalizationDto localizationDto;

    @Before
    public void init() throws Exception {
        this.token= obtainAccessToken("admin@localization.com", "123456");
    }

    @Test
    @Transactional
    public void shouldCreate()throws Exception{
        createLanguage();

        LocalizationDto localizationDto = new LocalizationDto();
        localizationDto.setLangKey("key.hello");
        localizationDto.setValue("Hello");
        localizationDto.setLanguageId(this.language.getId());
        localizationDto.setProjectId(this.project.getId());

        String localizationDtoStr =
        this.mvc.perform(
                post("/api/protected/localizations")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(localizationDto))
                )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andReturn()
                .getResponse()
                .getContentAsString();

        this.localizationDto = objectMapper.readValue(localizationDtoStr, LocalizationDto.class);
    }


    @Test
    @Transactional
    public void shouldUpdate()throws Exception{
        shouldCreate();

        LocalizationDto localizationDto = objectMapper.readValue(getResponseAsString("/api/protected/localizations/" + this.localizationDto.getId()), LocalizationDto.class);
        localizationDto.setValue("Hello123");

        this.mvc.perform(
                put("/api/protected/localizations")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(localizationDto))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.value").value("Hello123"));
    }



    private void createLanguage() throws Exception{
        createProject();

        Language language = new Language();
        language.setCode("ln1");
        language.setName("language 1");
        language.setProject(this.project);

        String languageStr =
                this.mvc.perform(
                        post(LanguageApiTest.api)
                                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getObjectAsString(language))
                )
                        .andExpect(jsonPath("$.id").isNotEmpty())
                        .andExpect(jsonPath("$.code").value("ln1"))
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        this.language = objectMapper.readValue(languageStr, Language.class);

    }

    private void createProject() throws Exception{

        // First create project
        Project project = new Project();
        project.setName("test project");
        project.setDescription("project description");
        project.setUrl("test-project.com");
        project.setCode("prj01");

        String projectStr =
                this.mvc.perform(
                        post(ProjectApiTest.api)
                                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(getObjectAsString(project))
                )
                        .andReturn()
                        .getResponse()
                        .getContentAsString();

        this.project = objectMapper.readValue(projectStr, Project.class);
    }
}
