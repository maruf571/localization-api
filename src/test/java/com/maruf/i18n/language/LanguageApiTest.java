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

    @Before
    public void init() throws Exception{
        this.token= obtainAccessToken("admin@localization.com", "123456");
    }

    @Test
    @Transactional
    public void shouldCreate() throws Exception{
        // First create project
        Project project = new Project();
        project.setName("tets project");
        project.setDescription("project description");
        project.setUrl("test-project.com");
        project.setCode("prj01");
        this.mvc.perform(
                post(ProjectApiTest.api)
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(project))
        ).andReturn();

        Page<Project> projects = objectMapper.readValue(getResponseAsString(ProjectApiTest.api), new TypeReference<CustomPageImpl<Project>>(){});
        Project existingProject = projects.getContent().get(0);


        Language language = new Language();
        language.setCode("ln1");
        language.setName("language 1");
        language.setProject(existingProject);

        this.mvc.perform(
                post(LanguageApiTest.api)
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(language))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.code").value("ln1"));
    }

    @Test
    @Transactional
    public void shouldUpdate() throws Exception{
        shouldCreate();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(ProjectApiTest.api), new TypeReference<CustomPageImpl<Project>>(){});
        Project existingProject = projects.getContent().get(0);

        List<Language> languages = objectMapper.readValue(getResponseAsString(LanguageApiTest.api + "?projectId=" + existingProject.getId()), new TypeReference<List<Language>>(){});
        Language language = languages.get(0);
        language.setCode("ln2");
        this.mvc.perform(
                put(LanguageApiTest.api)
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(language))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.code").value("ln2"));
    }

    @Test
    @Transactional
    public void shouldFindById() throws Exception{
        shouldCreate();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(ProjectApiTest.api), new TypeReference<CustomPageImpl<Project>>(){});
        Project existingProject = projects.getContent().get(0);
        List<Language> languages = objectMapper.readValue(getResponseAsString(LanguageApiTest.api + "?projectId=" + existingProject.getId()), new TypeReference<List<Language>>(){});

        this.mvc.perform(
                get(LanguageApiTest.api + languages.get(0).getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.id").value(languages.get(0).getId()));
    }


    @Test
    @Transactional
    public void shouldDelete() throws Exception{
        shouldCreate();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(ProjectApiTest.api), new TypeReference<CustomPageImpl<Project>>(){});
        Project existingProject = projects.getContent().get(0);
        List<Language> languages = objectMapper.readValue(getResponseAsString(LanguageApiTest.api + "?projectId=" + existingProject.getId()), new TypeReference<List<Language>>(){});

        this.mvc.perform(
                delete(LanguageApiTest.api + languages.get(0).getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }
}
