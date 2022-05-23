package com.example.classroom.restClients;

import com.example.classroom.models.student;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import com.example.classroom.models.StudentRepository;
import com.example.classroom.models.StudentService;
import com.example.classroom.models.Teacher;
import com.example.classroom.models.TeacherRepository;
import com.example.classroom.models.TeacherService;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
public class Controller {
    @Autowired
    StudentRepository studentRepository;
    private StudentService studentService;
    TeacherRepository teacherRepository;
    private TeacherService teacherService;
    @Autowired
    public Controller(StudentService studentService){
        this.studentService = studentService;}
    public Controller(TeacherService teacherService){
      this.teacherService = teacherService;
    }
    @GetMapping(value ="/test")
    public String test() {
        return "SUCCESS";
    }
    @GetMapping(value ="/teachers")
    public @ResponseBody Iterable<Teacher> getAllTeachers(){
      TeacherService teacherService=new TeacherService(teacherRepository);
      return teacherService.getAllTeachers();

    }

    @GetMapping(value="/students")
    public @ResponseBody Iterable<student> getAllStudents() {
      StudentService studentService = new StudentService(studentRepository);
      return studentService.getAllStudents();
    }

    @GetMapping(value="/studentFile", consumes="application/pdf")
    public void getStudents() {
      studentService.fileStudnet();
      // return new ResponseEntity<String>(HttpStatus.OK);
    }

    @PostMapping("/upload/db")
  public ResponseEntity uploadToDB(@RequestParam String name, @RequestParam String phone, @RequestParam String email, @RequestParam String usn,@RequestParam("file") MultipartFile file) {
	student stud = new student();
  stud.setEMAIL(email);
  stud.setPHONE(phone);
  stud.setNAME(name);
  stud.setUSN(usn);
	String fileName = StringUtils.cleanPath(file.getOriginalFilename());
	try {
    byte[] inFileBytes = file.getBytes();
    // byte[] inFileBytes = "Hello Dear".getBytes(); 
    byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);
	  stud.setAssignment(encoded);
	} catch (Exception e) {
		e.printStackTrace();
	}
  studentRepository.save(stud);
	String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
			.path("/files/download/")
			.path(fileName).path("/db")
			.toUriString();
	return ResponseEntity.ok(fileDownloadUri);
}

}
