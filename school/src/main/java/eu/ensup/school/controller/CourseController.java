package eu.ensup.school.controller;

import eu.ensup.school.exception.AssociationException;
import eu.ensup.school.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RequestMapping("/courses")
@RestController
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/associate/{studentId}/{courseId}")
    public ResponseEntity<HttpStatus> associateStudentCourse(@PathVariable Long studentId, @PathVariable Long courseId) {
        try {
            courseService.associateStudentCourse(studentId, courseId);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } catch (AssociationException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

    }


}
