package com.maruf.i18n.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.maruf.i18n.AbstractTest;
import com.maruf.i18n.CustomPageImpl;
import com.maruf.i18n.project.entity.Project;
import com.maruf.i18n.security.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProjectApiTest extends AbstractTest {

    private static final String api = "/api/protected/projects/";

    @Before
    public void init() throws Exception {
        this.token= obtainAccessToken("admin@localization.com", "123456");
    }

    @Test
    @Transactional
    public void shouldCreateProject() throws Exception{
        Project project = new Project();
        project.setName("tets project");
        project.setDescription("project description");
        project.setUrl("test-project.com");
        project.setCode("prj01");
        this.mvc.perform(
                post(api)
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(project))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("tets project"));
    }

    @Test
    @Transactional
    public void shouldUpdateProject()throws Exception{
        shouldCreateProject();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        Project project = projects.getContent().get(0);
        project.setName("updated project");

        this.mvc.perform(
                put(api)
                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsString(project))
        )
                .andExpect(jsonPath("$.name").value("updated project"));
    }


    @Test
    @Transactional
    public void shouldFindAll() throws Exception{
        shouldCreateProject();
        this.mvc.perform(
                get("/api/protected/projects")
                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").value(1));
    }

    @Test
    @Transactional
    public void shouldFindById()throws Exception{

        shouldCreateProject();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        Project project = projects.getContent().get(0);

        this.mvc.perform(
                get(api + "/" + project.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.id").value(project.getId()));

    }



    @Test
    @Transactional
    public void shouldDeleteProject()throws Exception{
        shouldCreateProject();
        Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        Project project = projects.getContent().get(0);
        this.mvc.perform(
                delete(api  + project.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());

    }

}
