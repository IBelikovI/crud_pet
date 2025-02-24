package com.example.crud_pet.rest;


import com.example.crud_pet.api.StudentFilter;
import com.example.crud_pet.api.TeacherFilter;
import com.example.crud_pet.entity.Student;
import com.example.crud_pet.entity.Teacher;
import com.example.crud_pet.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private Service service;
    private TeacherFilter teacherFilter;

//    public StudentRestController(Service service) {
//        this.service = service;
//    }

    public StudentRestController(Service service, TeacherFilter teacherFilter) {
        this.service = service;
        this.teacherFilter = teacherFilter;
    }

    @GetMapping("/students")
    public List<Student> getAllStudents(){
        return service.getAllStudent();
    }

    @GetMapping("/teachers")
    public List<Teacher> getAllTeacher(){
        return service.getAllTeacher();
    }

//    @GetMapping("/students/id/{studentId}")
//    public Student getById(@PathVariable (name = "studentId") int id){
//        Student student = studentService.getStudent(id);
//        if (student == null){
//            throw new RuntimeException("student with id" + id + " not found");
//        }
//        return student;
//    }
//
//    @GetMapping("/students/firstName/{param}")
//    public List<Student> getByParam(@PathVariable (name = "param") String name){
//        List<Student> studentList = studentService.getAll();
//        List<Student> result = new ArrayList<>();
//        for(Student s: studentList){
//            if (s.getFirstName().equals(name)){
//                result.add(s);
//            }
//        } if (result.isEmpty()){
//            throw new RuntimeException("Name " + name + "not found");
//        }
//        return result;
//    }

    @GetMapping("/teachers/filter")
    public List<Teacher> getByFilter(@RequestBody Teacher teacher){
        List<Teacher> teachers = teacherFilter.getFilteredTeacher(teacher);
        return teachers;
    }
    @GetMapping("/students/filter")
    public List<Student> getByFilter(@RequestBody StudentFilter studentFilter){
        List<Student> students = service.getFilteredStudent(studentFilter);
        return students;
    }

    @PostMapping("/students")
    public void addStudent(@RequestBody Student student){
        student.setId(0);
        service.createStudent(student);
    }

    @PostMapping("/teachers")
    public void addTeacher(@RequestBody Teacher teacher){
        teacher.setId(0);
        service.createTeacher(teacher);
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student){
        Student updateStudent = service.createStudent(student);
        return updateStudent;
    }

    @PutMapping("/teachers")
    public Teacher updateTeacher(@RequestBody Teacher teacher){
        Teacher updateTeacher = service.createTeacher(teacher);
        return updateTeacher;
    }

    @DeleteMapping("/students/{studentId}")
    public void deleteStudent(@PathVariable (name = "studentId") int id){
        Student student = service.getStudent(id);
        if (student == null){
            throw new RuntimeException("student with id " + id + " not found");
        }
            service.delete(id);
    }

}
