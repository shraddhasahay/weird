package com.example.classroom.models;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;
    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }
    public Iterable<Teacher> getAllTeachers()   
    {  
        ArrayList<Teacher> teachers = new ArrayList<Teacher>();
        teacherRepository.findAll().forEach(teacher -> teachers.add(teacher));;
        return teachers;
    }
    public void addTeacher(Teacher teach) {
        teacherRepository.save(teach);
    }
}
