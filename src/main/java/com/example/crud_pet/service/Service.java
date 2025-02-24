package com.example.crud_pet.service;

import com.example.crud_pet.api.StudentFilter;
import com.example.crud_pet.entity.Student;
import com.example.crud_pet.entity.Teacher;

import java.util.List;


public interface Service {

    Student createStudent(Student student);

    Teacher createTeacher(Teacher teacher);

    List<Student> getAllStudent();

    List<Teacher> getAllTeacher();

    Student getStudent(int id);

    void delete(int id);

    List<Student> getFilteredStudent(StudentFilter studentFilter);

}
