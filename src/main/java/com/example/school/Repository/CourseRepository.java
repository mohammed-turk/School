package com.example.school.Repository;

import com.example.school.Model.Address;
import com.example.school.Model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Course findCourseById(Integer id);
}
