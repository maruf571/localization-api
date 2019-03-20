package com.maruf.i18n.api;

import com.maruf.i18n.entity.Project;
import com.maruf.i18n.security.config.WebSecurityConfig;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("it")
public class ProjectApiIT extends AbstractIntegrationTest {

    private Project project;

    @Before
    public void init() throws Exception {
        this.token= obtainAccessToken("admin@localization.com", "123456");
    }

    @Test
    @Transactional
    public void shouldCreate() throws Exception{
        Project project = new Project();
        project.setName("test project");
        project.setDescription("project description");
        project.setCode("prj01");
        project.setUrl("test-project.com");

        String projectStr =
        this.mvc.perform(
                post("/api/protected/projects/")
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getObjectAsString(project))
        )
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("test project"))
                .andExpect(jsonPath("$.description").value("project description"))
                .andExpect(jsonPath("$.code").value("prj01"))
                .andExpect(jsonPath("$.url").value("test-project.com"))
                .andReturn()
                .getResponse()
                .getContentAsString();

        this.project = objectMapper.readValue(projectStr, Project.class);
    }

    @Test
    @Transactional
    public void shouldUpdate()throws Exception{
        shouldCreate();
        //Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        Project project = objectMapper.readValue(getResponseAsString("/api/protected/projects/" + this.project.getId()), Project.class);
        project.setName("test project 1");
        project.setDescription("project description 1");
        project.setCode("prj01 1");
        project.setUrl("test-project1.com");

        this.mvc.perform(
                put("/api/protected/projects/")
                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                .contentType(MediaType.APPLICATION_JSON)
                .content(getObjectAsString(project))
        )
                .andExpect(jsonPath("$.id").value(this.project.getId()))
                .andExpect(jsonPath("$.name").value("test project 1"))
                .andExpect(jsonPath("$.description").value("project description 1"))
                .andExpect(jsonPath("$.code").value("prj01 1"))
                .andExpect(jsonPath("$.url").value("test-project1.com"));
    }


    @Test
    @Transactional
    public void shouldFindById()throws Exception{
        shouldCreate();
        this.mvc.perform(
                get("/api/protected/projects/" + this.project.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(jsonPath("$.id").value(project.getId()))
                .andExpect(jsonPath("$.name").value(project.getName()))
                .andExpect(jsonPath("$.description").value(project.getDescription()))
                .andExpect(jsonPath("$.code").value(project.getCode()))
                .andExpect(jsonPath("$.url").value(project.getUrl()));

    }

    @Test
    @Transactional
    public void shouldFindAllProject() throws Exception{
        shouldCreate();
        //Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        this.mvc.perform(
                get("/api/protected/projects")
                .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.size()").value(1))
                .andExpect(jsonPath("$.numberOfElements").value(1));
    }




    @Test
    @Transactional
    public void shouldDeleteProject()throws Exception{
        shouldCreate();
        //Page<Project> projects = objectMapper.readValue(getResponseAsString(api), new TypeReference<CustomPageImpl<Project>>(){});
        this.mvc.perform(
                delete("/api/protected/projects/"  + this.project.getId())
                        .header(WebSecurityConfig.JWT_TOKEN_HEADER_PARAM, getBearer(token))
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

}
