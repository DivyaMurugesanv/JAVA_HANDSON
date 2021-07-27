package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public List<Student> getStudentList() {
        return studentRepository.findAll();
    }

    @GetMapping("/get/{id}")
    public Optional<Student> getStudentbyId(@PathVariable Long id) {
        System.out.println(id);
        return studentRepository.findById(id);
    }
    @GetMapping("/get/names")
    public List<String> getAllNames() {
        return studentRepository.getAllNames();
    }

    @PostMapping("/update")
    public boolean update(@RequestBody Student student) {
        studentRepository.save(student);
        return true;
    }
    @PostMapping("/add")
    public boolean add(@RequestBody Student student) {
        studentRepository.save(student);
        return true;
    }
    @DeleteMapping("/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        studentRepository.deleteById(id);
        return true;
    }
}
