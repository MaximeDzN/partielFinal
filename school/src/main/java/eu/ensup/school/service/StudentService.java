package eu.ensup.school.service;

import eu.ensup.school.exception.CourseNotFoundException;

public interface StudentService {

    //STATS
    long countStudents();
    long countStudentsWithoutCourse();
    long countStudentsByCourse(long courseId) throws CourseNotFoundException;

}
