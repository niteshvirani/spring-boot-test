package com.nv.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shouldSaveStudent() throws Exception {
        // Given
        StudentDto studentDto = new StudentDto("John", "Doe", "john@mail.com", 1);
        StudentResponseDto responseDto = new StudentResponseDto("John", "Doe", "john@mail.com");

        Mockito.when(studentService.saveStudent(studentDto))
                .thenReturn(responseDto);

        // When
        mockMvc.perform(MockMvcRequestBuilders.post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDto))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated());
    }

    @Test
    void shouldFindAllStudents() throws Exception {
        // Given
        List<StudentResponseDto> students = new ArrayList<>();
        students.add(new StudentResponseDto("John", "Doe", "john@mail.com"));
        Mockito.when(studentService.findAllStudent()).thenReturn(students);

        // When
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/api/students")).andReturn();

        // Then
        Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    void shouldFindStudentById() throws Exception {
        // Given
        Integer studentId = 1;
        StudentResponseDto responseDto = new StudentResponseDto("John", "Doe", "john@mail.com");

        // Mock the calls
        Mockito.when(studentService.findStudentById(studentId))
                .thenReturn(responseDto);

        // When
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students/" + studentId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(responseDto)));
    }

}