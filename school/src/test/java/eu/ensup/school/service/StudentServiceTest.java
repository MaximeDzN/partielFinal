package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.exception.CourseNotFoundException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private StudentServiceImpl studentService;


    @Test
    @DisplayName("countStudents__success")
    void countStudents() {
        long expectedResult = 6L;
        when(studentRepository.count()).thenReturn(6L);
        long result = studentService.countStudents();
        assertEquals(expectedResult, result);
        verify(studentRepository, times(1)).count();
    }

    @Test
    @DisplayName("countStudentWithoutCourse__success")
    void countStudentWithoutCourse() {
        long expectedResult = 6L;
        when(studentRepository.countStudentByCoursesIsNull()).thenReturn(6L);
        long result = studentService.countStudentsWithoutCourse();
        assertEquals(expectedResult, result);
        verify(studentRepository, times(1)).countStudentByCoursesIsNull();
    }

    @Test
    @DisplayName("countStudentInCourse__success")
    void countStudentInCourse() throws CourseNotFoundException {
        long expectedResult = 6L;
        long expectedCourseId = 1L;
        Course expectedCourse = Course.builder().id(expectedCourseId).theme("math").hours(150).students(new ArrayList<>()).build();
        when(courseRepository.findById(expectedCourseId)).thenReturn(Optional.ofNullable(expectedCourse));
        when(studentRepository.countStudentByCourses(expectedCourse)).thenReturn(6L);
        long result = studentService.countStudentsByCourse(expectedCourseId);
        assertEquals(expectedResult, result);
        verify(courseRepository, times(1)).findById(expectedCourseId);
        verify(studentRepository, times(1)).countStudentByCourses(expectedCourse);
    }

//    @Test
//    @DisplayName("countStudentInCourse__Exception")
//    void countStudentInCourseException() {
//        long expectedCourseId = 1L;
//        when(courseRepository.findById(expectedCourseId)).thenReturn(Optional.empty());
//        try {
//            studentService.countStudentsByCourse(expectedCourseId);
//        } catch (CourseNotFoundException e) {
//            CourseNotFoundException exception = assertThrows(CourseNotFoundException.class, () -> {
//            });
//            assertEquals(exception.getMessage(), "e");
//            verify(studentRepository, times(1)).findById(expectedCourseId);
//        }
//        fail();
//    }

}