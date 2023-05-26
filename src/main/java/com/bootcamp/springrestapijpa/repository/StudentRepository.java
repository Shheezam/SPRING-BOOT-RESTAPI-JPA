package com.bootcamp.springrestapijpa.repository;

import com.bootcamp.springrestapijpa.student.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
