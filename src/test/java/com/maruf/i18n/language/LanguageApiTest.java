package com.maruf.i18n.language;

import com.fasterxml.jackson.core.type.TypeReference;
import com.maruf.i18n.AbstractTest;
import com.maruf.i18n.CustomPageImpl;
import com.maruf.i18n.language.entity.Language;
import com.maruf.i18n.project.ProjectApi;
import com.maruf.i18n.project.ProjectApiTest;
import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.security.config.WebSecurityConfig;
import org.apache.commons.codec.language.bm.Lang;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LanguageApiTest extends AbstractTest {

    public static final String api = "/api/protected/languages/";

    private Project project;

    private Language language;

    @Before
    public void init() throws Exception{
        this.token= obtainAccessToken("admin@localization.com", "123456");
    }

    @Test
    @Transactional
    public void shouldCreate() throws Exception{
        createProject();

        Language language = new Language();
        language.setCode("ln1");
        language.setName("language 1");
        language.setProject(this.project);
        language.setIsRtl(false);
        language.setIsActive(true);

        String languageStr =
        this.mvc.perform(
                post("/api/protected/languages")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(language))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.code").value("ln1"))
                .andExpect(jsonPath("$.name").value("language 1"))
                .andExpect(jsonPath("$.isRtl", Matchers.is(false)))
                .andExpect(jsonPath("$.isActive").doesNotExist()) // as isActive write only
                .andExpect(jsonPath("$.project").value(this.project))
                .andReturn()
                .getResponse()
                .getContentAsString();

        this.language = objectMapper.readValue(languageStr, Language.class);

    }

    @Test
    @Transactional
    public void shouldUpdate() throws Exception{
        shouldCreate();

        Language language = objectMapper.readValue(getResponseAsString("/api/protected/languages/" + this.language.getId()), Language.class);
        language.setName("language 2");
        language.setCode("ln2");
        language.setIsRtl(true);


        this.mvc.perform(
                put(LanguageApiTest.api)
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(language))
        )
                .andExpect(jsonPath("$.id").value(this.language.getId()))
                .andExpect(jsonPath("$.name").value("language 2"))
                .andExpect(jsonPath("$.code").value("ln2"))
                .andExpect(jsonPath("$.isRtl", Matchers.is(true)));
    }

    @Test
    @Transactional
    public void shouldFindById() throws Exception{
        shouldCreate();

        this.mvc.perform(
                get(LanguageApiTest.api + this.language.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(jsonPath("$.id").value(this.language.getId()))
                .andExpect(jsonPath("$.name").value(this.language.getName()))
                .andExpect(jsonPath("$.code").value(this.language.getCode()))
                .andExpect(jsonPath("$.isRtl").value(this.language.getIsRtl()))
                .andExpect(jsonPath("$.project").value(this.language.getProject()));
    }


    @Test
    @Transactional
    public void shouldFindAll() throws Exception{
        shouldCreate();
        //List<Language> languages = objectMapper.readValue(getResponseAsString(LanguageApiTest.api + "?projectId=" + this.project.getId()), new TypeReference<List<Language>>(){});

        this.mvc.perform(
                get(LanguageApiTest.api + "/projects/" + this.project.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].id").value(this.language.getId()))
                .andExpect(jsonPath("$[0].name").value(this.language.getName()))
                .andExpect(jsonPath("$[0].code").value(this.language.getCode()))
                .andExpect(jsonPath("$[0].isRtl").value(this.language.getIsRtl()))
                .andExpect(jsonPath("$[0].project").value(this.language.getProject()));
    }



    @Test
    @Transactional
    public void shouldDelete() throws Exception{
        shouldCreate();
        this.mvc.perform(
                delete(LanguageApiTest.api + this.language.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
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
