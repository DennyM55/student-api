package org.learn.studentapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.ObjectMapper;

import jakarta.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CourseEnrollmentIntegrationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private EntityManager entityManager;

    @Test
    void enrollStudentCreatesStudentCourseJoinRow() throws Exception {
        Long teacherId = postJsonAndReadId("/api/teachers",
                """
                {
                  "name": "Integration Teacher",
                  "subject": "Computer Science"
                }
                """);

        Long studentId = postJsonAndReadId("/api/students",
                """
                {
                  "name": "Integration Student",
                  "email": "integration.student@example.com",
                  "age": 21,
                  "phoneNumber": "9876543210"
                }
                """);

        Long courseId = postJsonAndReadId("/api/courses?teacherId=" + teacherId,
                """
                {
                  "title": "Integration Course",
                  "description": "Course used for enrollment integration testing",
                  "durationWeeks": 8
                }
                """);

        mockMvc.perform(post("/api/courses/{courseId}/enroll/{studentId}", courseId, studentId))
                .andExpect(status().isOk());

        entityManager.flush();

        Integer joinRowCount = jdbcTemplate.queryForObject(
                "select count(*) from student_courses where course_id = ? and student_id = ?",
                Integer.class,
                courseId,
                studentId
        );

        assertThat(joinRowCount).isEqualTo(1);
    }

    private Long postJsonAndReadId(String url, String json) throws Exception {
        String response = mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        JsonNode body = objectMapper.readTree(response);
        return body.get("id").asLong();
    }
}
