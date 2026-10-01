package com.example.school.Service;

import com.example.school.API.APIException;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.StudentRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public List<Student> get(){
        return studentRepository.findAll();
    }

    public void add(Student student){
        studentRepository.save(student);
    }

    public void update(Integer id, Student student){
        Student oldStudent = studentRepository.findStudentById(id);
        if (oldStudent == null)
            throw new APIException("student not found");
        oldStudent.setName(student.getName());
        oldStudent.setAge(student.getAge());
        oldStudent.setMajor(student.getMajor());
        studentRepository.save(oldStudent);
    }

    public void delete(Integer id){
        Student student = studentRepository.findStudentById(id);
        if (student == null)
            throw new APIException("student not found");

        studentRepository.delete(student);
    }

    public void assignStudentCourse(Integer studentId, Integer courseId){
        Student student = studentRepository.findStudentById(studentId);
        Course course = courseRepository.findCourseById(courseId);

        if (student == null || course == null)
            throw new APIException("invalid assignment");

        student.getCourseSet().add(course);
        course.getStudentSet().add(student);

        studentRepository.save(student);
        courseRepository.save(course);
    }

    public void updateMajor(Integer studentId, String major){
        Student student = studentRepository.findStudentById(studentId);
        if (student == null)
            throw new APIException("student not found");

        for (Course c : student.getCourseSet())
            c.getStudentSet().remove(student);
        student.setMajor(major);
        student.getCourseSet().clear();
        studentRepository.save(student);


    }

}
