package com.bootcamp.springrestapijpa.service;


import com.bootcamp.springrestapijpa.course.Course;

import com.bootcamp.springrestapijpa.repository.StudentRepository;
import com.bootcamp.springrestapijpa.student.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseService courseService;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseService courseService) {
        this.studentRepository = studentRepository;
        this.courseService = courseService;
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void enrollCourse(Student student, Course course) {
        Course existingCourse = courseService.getCourseByName(course.getName());
        if (existingCourse != null) {
            student.setCourse(existingCourse);
            studentRepository.save(student);
        } else {
            // Handle the case where the course does not exist
            throw new IllegalArgumentException("Course does not exist");
        }
    }
    public void dropCourse(Student student, Course course) {
        Course existingCourse = courseService.getCourseByName(course.getName());
        if (existingCourse != null) {
            if (student.getCourse() != null && student.getCourse().equals(existingCourse)) {
                student.setCourse(null);
                studentRepository.save(student);
            } else {
                throw new IllegalArgumentException("Student is not enrolled in the specified course");
            }
        } else {
            throw new IllegalArgumentException("Course does not exist");
        }
    }


}
