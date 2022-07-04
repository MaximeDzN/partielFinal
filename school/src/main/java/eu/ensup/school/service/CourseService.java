package eu.ensup.school.service;

import eu.ensup.school.exception.AssociationException;

public interface CourseService {

    void associateStudentCourse(Long studentId, Long courseId) throws AssociationException;

}
