package com.nv.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResponseDto {
    private String firstName;
    private String lastName;
    private String email;

    public StudentResponseDto(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
