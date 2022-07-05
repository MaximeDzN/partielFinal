package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.Student;
import eu.ensup.school.exception.AssociationException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import eu.ensup.school.repository.TeacherRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {

    @Mock
    private TeacherRepository teacherRepository;

    @InjectMocks
    private TeacherServiceImpl teacherService;


    @Test
    @DisplayName("countTeachers__success")
    void countTeachers(){
        long expectedResult = 6L;
        when(teacherRepository.count()).thenReturn(6L);
        long result = teacherService.countTeachers();
        assertEquals(expectedResult,result);
        verify(teacherRepository,times(1)).count();
    }

}