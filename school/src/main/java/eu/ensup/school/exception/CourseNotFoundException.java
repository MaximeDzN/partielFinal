package eu.ensup.school.exception;

public class CourseNotFoundException extends Exception{
    public CourseNotFoundException(String error){
        super(error);
    }
}
