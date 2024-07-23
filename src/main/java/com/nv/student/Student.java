package com.nv.student;

import com.nv.school.School;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Student {
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private School school;

    public Student(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
