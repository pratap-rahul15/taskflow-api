
package com.example.taskflow.task;

import com.example.taskflow.TaskflowApiApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = TaskflowApiApplication.class)
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldRejectBlankTitle() throws Exception {
        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "   ",
                                  "description": "Test task"
                                }
                                """))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectTitleLongerThan100Characters() throws Exception {
        String longTitle = "a".repeat(101);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "%s",
                                  "description": "Test task"
                                }
                                """.formatted(longTitle)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void shouldRejectDescriptionLongerThan500Characters() throws Exception {
        String longDescription = "a".repeat(501);

        mockMvc.perform(post("/api/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "title": "Valid title",
                                  "description": "%s"
                                }
                                """.formatted(longDescription)))
                .andExpect(status().isBadRequest());
    }
}
