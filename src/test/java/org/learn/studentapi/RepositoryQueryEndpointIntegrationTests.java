package org.learn.studentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class RepositoryQueryEndpointIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void getStudentByEmailUsesRepositoryQueryMethod() throws Exception {
        postJsonAndReadBody("/api/students",
                """
                {
                  "name": "Email Query Student",
                  "email": "email.query.student@example.com",
                  "age": 20,
                  "phoneNumber": "9123456789"
                }
                """);

        String response = mockMvc.perform(get("/api/students/email/{email}", "email.query.student@example.com"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode body = objectMapper.readTree(response);

        assertThat(body.get("name").asString()).isEqualTo("Email Query Student");
        assertThat(body.get("email").asString()).isEqualTo("email.query.student@example.com");
    }

    @Test
    void searchCoursesByTitleUsesRepositoryQueryMethod() throws Exception {
        Long teacherId = postJsonAndReadId("/api/teachers",
                """
                {
                  "name": "Query Teacher",
                  "subject": "Backend Development"
                }
                """);

        postJsonAndReadBody("/api/courses?teacherId=" + teacherId,
                """
                {
                  "title": "Spring Boot Query Course",
                  "description": "Matches the Spring keyword",
                  "durationWeeks": 6
                }
                """);

        postJsonAndReadBody("/api/courses?teacherId=" + teacherId,
                """
                {
                  "title": "Database Design Course",
                  "description": "Does not match the Spring keyword",
                  "durationWeeks": 4
                }
                """);

        String response = mockMvc.perform(get("/api/courses/search")
                        .param("keyword", "Spring"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode body = objectMapper.readTree(response);

        assertThat(body.size()).isEqualTo(1);
        assertThat(body.get(0).get("title").asString()).isEqualTo("Spring Boot Query Course");
    }

    private Long postJsonAndReadId(String url, String json) throws Exception {
        JsonNode body = postJsonAndReadBody(url, json);
        return body.get("id").asLong();
    }

    private JsonNode postJsonAndReadBody(String url, String json) throws Exception {
        String response = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        return objectMapper.readTree(response);
    }
}
