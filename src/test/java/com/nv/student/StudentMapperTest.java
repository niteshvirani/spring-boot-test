package com.nv.student;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentMapperTest {

    private StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        studentMapper = new StudentMapper();
    }

    @Test
    public void shouldMapStudentDtoToStudent() {
        // Given
        StudentDto dto = new StudentDto("John", "Doe", "john@mail.com", 1);

        // When
        Student student = studentMapper.toStudent(dto);

        // Then
        assertEquals(dto.getFirstName(), student.getFirstName());
        assertEquals(dto.getLastName(), student.getLastName());
        assertEquals(dto.getEmail(), student.getEmail());
        assertNotNull(student.getSchool());
        assertEquals(dto.getSchoolId(), student.getSchool().getId());
    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> studentMapper.toStudent(null));
        assertEquals("The student Dto should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapStudentToStudentResponseDto() {
        // Given
        Student student = new Student("Jane", "Smith", "jane@mail.com");

        // When
        StudentResponseDto studentResponseDto = studentMapper.toStudentResponseDto(student);

        // Then
        assertEquals(studentResponseDto.getFirstName(), student.getFirstName());
        assertEquals(studentResponseDto.getLastName(), student.getLastName());
        assertEquals(studentResponseDto.getEmail(), student.getEmail());
    }
}