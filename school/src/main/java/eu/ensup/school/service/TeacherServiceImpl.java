package eu.ensup.school.service;

import eu.ensup.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public long countTeachers() {
        return teacherRepository.count();
    }
}
