package com.maruf.localization.api;

import com.maruf.localization.entity.Language;
import com.maruf.localization.api.dto.LocalizationDto;
import com.maruf.localization.entity.Project;
import com.maruf.localization.security.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ActiveProfiles("it")
public class LocalizationApiIT extends AbstractIntegrationTest {

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
        // Project is not required for localization

        String localizationDtoStr =
        this.mvc.perform(
                post("/api/protected/localizations")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(localizationDto))
                )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.langKey").value("key.hello"))
                .andExpect(jsonPath("$.value").value("Hello"))
                .andExpect(jsonPath("$.languageId").value(this.language.getId()))
                .andReturn()
                .getResponse()
                .getContentAsString();

        this.localizationDto = objectMapper.readValue(localizationDtoStr, LocalizationDto.class);

    }


    @Test
    @Transactional
    public void shouldUpdate()throws Exception{
        shouldCreate();

        LocalizationDto localizationDto = objectMapper.readValue(
                getResponseAsString("/api/protected/localizations/" + this.localizationDto.getId() + "/language/" + this.language.getId()),
                LocalizationDto.class
        );
        localizationDto.setLangKey("key.hello123");
        localizationDto.setValue("Hello123");
        this.mvc.perform(
                put("/api/protected/localizations")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(localizationDto))
                )
                .andExpect(jsonPath("$.id").value(localizationDto.getId()))
                .andExpect(jsonPath("$.langKey").value("key.hello123"))
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
                        post("/api/protected/languages/")
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
                        post("/api/protected/projects/")
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
