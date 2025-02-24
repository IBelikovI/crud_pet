package com.example.crud_pet.api;

import com.example.crud_pet.entity.Teacher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TeacherFilter {


    List<Teacher> getFilteredTeacher(Teacher teacher);
}
