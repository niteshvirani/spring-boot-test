package com.nv.student;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<StudentResponseDto> saveStudent(@RequestBody StudentDto dto) {
        return new ResponseEntity<>(studentService.saveStudent(dto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<StudentResponseDto>> findAllStudent() {
        return ResponseEntity.ok(studentService.findAllStudent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDto> findStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.findStudentById(id));
    }
}
