package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.exception.CourseNotFoundException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public StudentServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public long countStudents() {
        return studentRepository.count();
    }

    @Override
    public long countStudentsWithoutCourse() {
        return studentRepository.countStudentByCoursesIsNull();
    }

    @Override
    public long countStudentsByCourse(long courseId) throws CourseNotFoundException{
        Optional<Course> courseOptional =courseRepository.findById(courseId);
        return courseOptional.map(studentRepository::countStudentByCourses).
                orElseThrow(() -> new CourseNotFoundException("Course with id : "+courseId+" not found"));
    }
}
