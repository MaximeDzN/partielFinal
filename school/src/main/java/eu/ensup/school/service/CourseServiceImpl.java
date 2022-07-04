package eu.ensup.school.service;

import eu.ensup.school.domain.Course;
import eu.ensup.school.domain.Student;
import eu.ensup.school.exception.AssociationException;
import eu.ensup.school.repository.CourseRepository;
import eu.ensup.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public void associateStudentCourse(Long studentId, Long courseId) throws AssociationException {
      Optional<Student> studentOptional = studentRepository.findById(studentId);
      Optional<Course> courseOptional = courseRepository.findById(courseId);
      Student student;
      Course course;
      if(studentOptional.isPresent() && courseOptional.isPresent()){
          student = studentOptional.get();
          course = courseOptional.get();
      } else {
          throw new AssociationException("Could not associate student: "+studentId+" and course: "+ courseId);
      }
      course.getStudents().add(student);
      courseRepository.save(course);
    }
}
