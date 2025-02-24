package com.example.crud_pet.service;

import com.example.crud_pet.api.StudentFilter;
import com.example.crud_pet.dao.StudentRepository;
import com.example.crud_pet.dao.TeacherRepository;
import com.example.crud_pet.entity.Student;
import com.example.crud_pet.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {

    StudentRepository studentRepository;
    TeacherRepository teacherRepository;

//    @Autowired
//    public ServiceImpl(StudentRepository studentRepository){
//        this.studentRepository = studentRepository;
//    }
//
//    @Autowired
//    public ServiceImpl(TeacherRepository teacherDAO) {
//        this.teacherRepository = teacherDAO;
//    }

    @Autowired
    public ServiceImpl(StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Student createStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    @Override
    public Teacher createTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
        return teacher;
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    @Override
    public List<Teacher> getAllTeacher() {
        return teacherRepository.findAll();
    }


    @Override
    public Student getStudent(int id) {
        Optional<Student> students = studentRepository.findById(id);
        Student newStudent = null;
        if(students.isPresent()){
            newStudent = students.get();
        } else
            throw new RuntimeException("Student not found");
        return newStudent;
    }

    @Override
    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public List<Student> getFilteredStudent(StudentFilter studentFilter) {
        List<Student> students = studentRepository.findAll();
        List<Student> filteredStudents = new ArrayList<>();
        for(Student s: students){
            if(studentFilter.getMinAge() < s.getAge() && s.getAge() <= studentFilter.getMaxAge()){
                filteredStudents.add(s);
            } else if(s.getFirstName().equals(studentFilter.getFirstName())){
                filteredStudents.add(s);
            } else if(s.getLastName().equals(studentFilter.getLastName())){
                filteredStudents.add(s);
            }
        }
        if (filteredStudents.isEmpty()){
            throw new RuntimeException("Student with parameters not found");
        }
        return filteredStudents;
    }

}
