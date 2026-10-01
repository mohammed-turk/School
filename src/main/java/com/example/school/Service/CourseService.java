package com.example.school.Service;

import com.example.school.API.APIException;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public List<Course> get(){
        return courseRepository.findAll();
    }

    public void add(Course course){
        courseRepository.save(course);
    }

    public void update(Integer courseId, Course course){
        Course oldCourse = courseRepository.findCourseById(courseId);
        if (oldCourse == null)
            throw new APIException("course not found");
        oldCourse.setName(course.getName());
        courseRepository.save(oldCourse);
    }

    public void delete(Integer id){
        Course course = courseRepository.findCourseById(id);
        if (course == null)
            throw new APIException("course not found");

        courseRepository.delete(course);
    }

    public String getCourseTeacher(Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        if (course == null)
            throw new APIException("course not found");

        String teacherName = course.getTeacher().getName();
        return teacherName;

    }

    public Set<Student> getCourseStudents(Integer courseId){
        Course course = courseRepository.findCourseById(courseId);
        if (course == null)
            throw new APIException("course not found");

        Set<Student> students = course.getStudentSet();
        return students;
    }
}
