package com.nv.student;

import com.nv.school.School;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto) {
        if (dto == null) {
            throw new NullPointerException("The student Dto should not be null");
        }
        var student = new Student();
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setFirstName(dto.getFirstName());

        var school = new School();
        school.setId(dto.getSchoolId());

        student.setSchool(school);

        return student;
    }

    public StudentResponseDto toStudentResponseDto(Student student) {
        return new StudentResponseDto(
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
