package com.example.nplus1.owner;

import com.example.nplus1.dog.Subject;
import com.example.nplus1.dog.SubjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    StudentService studentService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @BeforeEach
    void init() {
        Student student1 = studentRepository.save(new Student("학생1"));
        Student student2 = studentRepository.save(new Student("학생2"));

        for (int i = 0; i < 3; i++) {
            Subject subject = subjectRepository.save(new Subject(student1, "수업" + i));
            student1.addSubject(subject);
        }
        for (int i = 3; i < 6; i++) {
            Subject subject = subjectRepository.save(new Subject(student2, "수업" + i));
            student2.addSubject(subject);
        }
    }

    @Test
    void test() throws Exception {
        List<String> subjects = studentService.findAllSubject();

        for (String subject : subjects) {
            System.out.println("subject = " + subject);
        }
    }

}