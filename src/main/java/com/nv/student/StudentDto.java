package com.nv.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private String firstName;
    private String lastName;
    private String email;
    private Integer schoolId;

    public StudentDto(String firstName, String lastName, String email, Integer schoolId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.schoolId = schoolId;
    }
}
