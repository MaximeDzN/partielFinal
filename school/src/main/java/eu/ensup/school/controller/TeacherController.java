package eu.ensup.school.controller;

import eu.ensup.school.service.TeacherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RequestMapping("/teachers")
@RestController
public class TeacherController {


    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    //STATS
    @GetMapping("/count/all")
    public ResponseEntity<Long> countTeachers(){
        return new ResponseEntity<>(teacherService.countTeachers(), HttpStatus.OK);
    }

}
