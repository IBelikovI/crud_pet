package com.example.crud_pet.api;

import com.example.crud_pet.dao.TeacherRepository;
import com.example.crud_pet.entity.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
@ConditionalOnProperty(name = "filter.mode", havingValue = "partly")
public class PartialMatch implements TeacherFilter{

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getFilteredTeacher(Teacher teacher) {
        List<Teacher> filteredTeachers = new ArrayList<>();
        List<Teacher> allTeacher = teacherRepository.findAll();
        String word = "";
        if (teacher != null) {
            if (teacher.getFirstName() != null) {
                word = teacher.getFirstName();
            }
            if (teacher.getSecondName() != null) {
                word += teacher.getSecondName();
            }
        }
        char[] enteredSim = word.toLowerCase().trim().toCharArray();
        for (Teacher t: allTeacher){
            int i = 0;
            char[] firstName = t.getFirstName().toLowerCase().trim().toCharArray();
            char[] secondName = t.getSecondName().toLowerCase().trim().toCharArray();
            char[] name = new char[firstName.length + secondName.length];
            System.arraycopy(firstName, 0, name, 0, firstName.length);
            System.arraycopy(secondName, 0, name, firstName.length-1, secondName.length);
            for (char c : name) {
                if (i == enteredSim.length-1){
                    filteredTeachers.add(t);
                    break;
                }else if (c != enteredSim[i]) {
                    i = 0;
                    continue;
                } else {
                    i++;
                }
            }
        }
        System.out.println("Input data: " + teacher.toString());
        for (Teacher t: allTeacher){
            System.out.print("teacher: ");
            System.out.println(t);
        }
        return filteredTeachers;
    }
}
