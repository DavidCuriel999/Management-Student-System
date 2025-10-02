package com.example.student_management.controllers;


import com.example.student_management.domain.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class StudentController {

    // Lista de recursos
    private List<Student> students = new ArrayList<>(Arrays.asList(
            new Student(1, "Laura Garcia", "laura.gmail.com", 21, "Computer Science"),
            new Student(2, "Carlos Fernandez", "carlos.f@gmail.com", 23, "Ingieneria Mecanica"),
            new Student(3,"Ana Martinez", "ana.m@gmail.com", 22,"Administracion de Empresas"),
            new Student(4, "Miguel Lopez","Miguel Lopez",24, "Ingenieria Electrica")
    ));

    // Mostrar todos los alumnos
    @GetMapping
    public List<Student> getStudents() {
        return students;
    }

    // Consultar un alumno por su email
    @GetMapping("/{email}")
    public Student getStudent(@PathVariable String email) {
        for (Student s : students) {
            if (s.getEmail().equals(email)) {
                return s;
            }

        }
        return null;
    }

    // Crear un nuevo Alumno
    @PostMapping
    public Student postStudent(@RequestBody Student student) {
        students.add(student);

        return student;

    }

    // Modificacion total de un alumno
    @PutMapping
    public Student putStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {
                s.setName(student.getName());
                s.setEmail(student.getEmail());
                s.setAge(student.getAge());
                s.setCourse(student.getCourse());

                return s;
            }
        }
        return null;
    }

    // Modificacion arcial de un alumno
    @PatchMapping
    public Student patchStudent(@RequestBody Student student) {
        for (Student s : students) {
            if (s.getId() == student.getId()) {

                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                if (student.getEmail() != null) {
                    s.setEmail(student.getEmail());
                }
                if(student.getAge() != 0) {
                    s.setAge(student.getAge());
                }
                if (student.getCourse() != null) {
                    s.setCourse(student.getCourse());
                }

                return s;
            }
        }
        return null;
    }

    //Eliminar un alumno a traves de su ID
    @DeleteMapping("/{id}")
    public Student deleteStudent(@PathVariable int id) {
        for (Student s : students) {
            if (s.getId() == id) {
                students.remove(s);

                return s;
            }
        }
        return null;
    }

}
