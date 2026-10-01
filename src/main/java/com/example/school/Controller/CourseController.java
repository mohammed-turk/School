package com.example.school.Controller;

import com.example.school.API.APIResponse;
import com.example.school.Model.Course;
import com.example.school.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/course")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(courseService.get());
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Course course){
        courseService.add(course);
        return ResponseEntity.status(200).body(new APIResponse("added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody @Valid Course course){
        courseService.update(id, course);
        return ResponseEntity.status(200).body(new APIResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        courseService.delete(id);
        return ResponseEntity.status(200).body(new APIResponse("deleted successfully"));
    }

    @GetMapping("/get-course-teacher/{courseId}")
    public ResponseEntity<?> getCourseTeacher(@PathVariable Integer courseId){
        return ResponseEntity.status(200).body(courseService.getCourseTeacher(courseId));
    }

    @GetMapping("/get-course-students/{courseId}")
    public ResponseEntity<?> getCourseStudents(@PathVariable Integer courseId){
        return ResponseEntity.status(200).body(courseService.getCourseStudents(courseId));

    }

}
