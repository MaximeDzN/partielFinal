package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.Student;
import eu.ensup.school.exception.AssociationException;
import eu.ensup.school.exception.CourseNotFoundException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
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

        Long studentId = 8L;
        Long courseId = 1L;
        Optional<Course> optionalCourse = Optional.of(Course.builder().id(studentId).theme("math").hours(150).students(new ArrayList<>()).build());
        Optional<Student> optionalStudent = Optional.of(Student.builder().id(courseId).firstName("maxime").lastName("dazin").build());
        Course expectedCourse = optionalCourse.get();
        expectedCourse.getStudents().add(optionalStudent.get());
        when(studentRepository.findById(studentId)).thenReturn(optionalStudent);
        when(courseRepository.findById(courseId)).thenReturn(optionalCourse);
        courseService.associateStudentCourse(studentId,courseId);
        verify(courseRepository,times(1)).findById(courseId);
        verify(studentRepository,times(1)).findById(studentId);
        verify(courseRepository,times(1)).save(optionalCourse.get());
    }

    @Test
    @DisplayName("associateStudentCourse__exception")
    void associateStudentCourse__exception()  {
        Long studentId = 8L;
        Long courseId = 1L;
        Optional<Course> optionalCourse = Optional.of(Course.builder().id(studentId).theme("math").hours(150).students(new ArrayList<>()).build());
        Optional<Student> optionalStudent = Optional.of(Student.builder().id(courseId).firstName("maxime").lastName("dazin").build());
        Course expectedCourse = optionalCourse.get();
        expectedCourse.getStudents().add(optionalStudent.get());
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());
        when(courseRepository.findById(courseId)).thenReturn(Optional.empty());
        try {
            courseService.associateStudentCourse(studentId,courseId);
        } catch (AssociationException e) {
            assertEquals("Could not associate student: 8 and course: 1",e.getMessage());
            verify(studentRepository,times(1)).findById(studentId);
            verify(courseRepository,times(1)).findById(courseId);
        }
    }

    @Test
    @DisplayName("associateStudentCourse__success")
    void AssociationException(){
        AssociationException thrown = Assertions.assertThrows(AssociationException.class, () -> {
            courseService.associateStudentCourse(1L,8L);
        });
        Assertions.assertEquals("Could not associate student: 1 and course: 8",thrown.getMessage());
    }


    @Test
    @DisplayName("countCourses__success")
    void countCourses(){
        long expectedResult = 6L;
        when(courseRepository.count()).thenReturn(6L);
        long result = courseService.countCourses();
        assertEquals(expectedResult,result);
        verify(courseRepository,times(1)).count();
    }

    @Test
    @DisplayName("countCoursesWithoutStudent__success")
    void countCoursesWithoutStudent(){
        long expectedResult = 6L;
        when(courseRepository.countCourseByStudentsIsNull()).thenReturn(6L);
        long result = courseService.countCourseWithoutStudents();
        assertEquals(expectedResult,result);
        verify(courseRepository,times(1)).countCourseByStudentsIsNull();
    }
}