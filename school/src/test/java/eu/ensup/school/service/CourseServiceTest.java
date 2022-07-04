package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.exception.AssociationException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CourseServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseServiceImpl courseService;

    @Test
    @DisplayName("associateStudentCourse__success")
    void associateStudentCourse__success() throws AssociationException {
        /*
        Long courseId = 8L;
        Date date = new Date();
        Course course = new Course(courseId,date,date,"Math",150, new ArrayList<>());
        when(courseRepository.save(course)).thenReturn(
        courseService.associateStudentCourse(1L,8L);
        verify(courseRepository).save(course);*/
    }
}