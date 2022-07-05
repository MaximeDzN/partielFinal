package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplicationTest;
import eu.ensup.school.controller.CourseController;
import eu.ensup.school.service.CourseServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@WebMvcTest(CourseController.class)
@ContextConfiguration(classes = SchoolApplicationTest.class)
public class CourseIntegrationTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseServiceImpl courseService;

    @Test
    public void errorRequest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/courses"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void associateSuccessful() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courses/associate/1/1"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test
    public void associateSuccessfulTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/courses/associate/abc/1"))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
