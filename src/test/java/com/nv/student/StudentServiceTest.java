package com.nv.student;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;
    @Mock
    private StudentMapper studentMapper;

    @Test
    public void should_successfully_save_student() {
        // Given
        StudentDto dto = new StudentDto("John", "Doe", "john@mail.com", 1);
        Student student = new Student("John", "Doe", "john@mail.com");
        Student savedStudent = new Student("John", "Doe", "john@mail.com");
        savedStudent.setId(1);

        // Mock the calls
        when(studentMapper.toStudent(dto)).thenReturn(student);
        when(studentRepository.save(student)).thenReturn(savedStudent);
        when(studentMapper.toStudentResponseDto(savedStudent))
                .thenReturn(new StudentResponseDto("John", "Doe", "john@mail.com"));

        // When
        StudentResponseDto responseDto = studentService.saveStudent(dto);

        // Then
        assertEquals(dto.getFirstName(), responseDto.getFirstName());
        assertEquals(dto.getLastName(), responseDto.getLastName());
        assertEquals(dto.getEmail(), responseDto.getEmail());

        verify(studentMapper, times(1)).toStudent(dto);
        verify(studentRepository, times(1)).save(student);
        verify(studentMapper, times(1)).toStudentResponseDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "Doe", "john@mail.com"));

        // Mock the calls
        when(studentRepository.findAll()).thenReturn(students);
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Doe", "john@mail.com"));

        // When
        List<StudentResponseDto> responseDtos = studentService.findAllStudent();

        // Then
        assertEquals(students.size(), responseDtos.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    public void should_return_student_by_id() {
        // Given
        Integer studentId = 1;
        Student student = new Student("John", "Doe", "john@mail.com");

        // Mock the calls
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        when(studentMapper.toStudentResponseDto(any(Student.class)))
                .thenReturn(new StudentResponseDto("John", "Doe", "john@mail.com"));

        // When
        StudentResponseDto dto = studentService.findStudentById(studentId);

        // Then
        assertEquals(dto.getFirstName(), student.getFirstName());
        assertEquals(dto.getLastName(), student.getLastName());
        assertEquals(dto.getEmail(), student.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
    }
}