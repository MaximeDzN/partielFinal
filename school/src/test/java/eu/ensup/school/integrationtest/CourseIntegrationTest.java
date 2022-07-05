package eu.ensup.school.integrationtest;

import eu.ensup.school.SchoolApplicationTest;
import eu.ensup.school.controller.CourseController;
import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.Student;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import eu.ensup.school.service.CourseService;
import eu.ensup.school.service.CourseServiceImpl;
import org.apache.http.HttpStatus;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.inject.Inject;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
//@AutoConfigureMockMvc(addFilters = false)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@WebMvcTest(controllers = {CourseController.class})
@ContextConfiguration(classes = { SchoolApplicationTest.class })
public class CourseIntegrationTest
{
    @Inject
    private MockMvc mockMvc;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @Test
    public void shouldRetrieveAnEventById() throws Exception {
        Course course = new Course();
        Student student = new Student();

        when(courseRepository.findById(course.getId())).thenReturn(Optional.of(course));
        when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

        // WHEN
        final MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/courses/associate/{studentId}/{courseId}")
                        .param("studentId", ""+student.getId())
                        .param("courseId", ""+course.getId())
                        .accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andReturn();

        // THEN
        assertThat(result.getResponse().getContentAsString(), equalTo("200"));

        verify(courseRepository).findById(course.getId());
        verify(studentRepository).findById(student.getId());
    }
}
