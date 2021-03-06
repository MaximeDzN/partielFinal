package eu.ensup.school.controller;

import eu.ensup.school.exception.CourseNotFoundException;
import eu.ensup.school.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RequestMapping("/students")
@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    //STATS
    @GetMapping("/count/all")
    public ResponseEntity<Long> countStudents(){
        return new ResponseEntity<>(studentService.countStudents(),HttpStatus.OK);
    }

    @GetMapping("/count/studentWithoutCourse")
    public ResponseEntity<Long> studentWithoutCourse(){
        return new ResponseEntity<>(studentService.countStudentsWithoutCourse(),HttpStatus.OK);
    }

    @GetMapping("/count/studentsInCourse/{courseId}")
    public ResponseEntity<Long> studentWithoutCourse(@PathVariable long courseId){
        try {
            return new ResponseEntity<>(studentService.countStudentsByCourse(courseId),HttpStatus.OK);
        } catch (CourseNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
