package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplicationTest;
import eu.ensup.school.controller.TeacherController;
import eu.ensup.school.service.StudentServiceImpl;
import eu.ensup.school.service.TeacherServiceImpl;
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
@WebMvcTest(TeacherController.class)
@ContextConfiguration(classes = SchoolApplicationTest.class)
public class TeacherControllerSIT
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherServiceImpl teacherService;

    @Test
    public void countAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/teachers/count/all"))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
