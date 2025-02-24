package com.example.crud_pet.api;

import com.example.crud_pet.dao.TeacherRepository;
import com.example.crud_pet.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@ConditionalOnProperty(name = "filter.mode", havingValue = "full")
public class FullMatch implements TeacherFilter{

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getFilteredTeacher(Teacher teacher) {
        List<Teacher> allTeachers = teacherRepository.findAll();
        List<Teacher> filteredTeachers = new ArrayList<>();
        String firstName = teacher.getFirstName();
        String secondName = teacher.getSecondName();
        if (firstName == null){
            throw new RuntimeException("First name is null for teacher: " + teacher + "\n please, enter full name(First name and second name).");
            //System.out.println("First name is null for teacher: " + teacher);
        } else
            firstName = firstName.toLowerCase();
        if (secondName == null){
            throw new RuntimeException("Second name is null for teacher: " + teacher + "\n please, enter full name(First name and second name).");
            //System.out.println("Second name is null for teacher: " + teacher);
        } else
            secondName = secondName.toLowerCase();
        for (Teacher t: allTeachers){
            if(!(firstName.equals(t.getFirstName().toLowerCase()))){
                continue;
            } else if(!(secondName.equals(t.getSecondName().toLowerCase()))){
                continue;
            }
            filteredTeachers.add(t);
        }
        return filteredTeachers;
    }
}
