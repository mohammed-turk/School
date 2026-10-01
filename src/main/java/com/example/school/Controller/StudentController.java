package com.example.school.Controller;

import com.example.school.API.APIResponse;
import com.example.school.Model.Course;
import com.example.school.Model.Student;
import com.example.school.Service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(studentService.get());
    }
    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Student student){
        studentService.add(student);
        return ResponseEntity.status(200).body(new APIResponse("added successfully"));

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody @Valid Student student){
        studentService.update(id, student);
        return ResponseEntity.status(200).body(new APIResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        studentService.delete(id);
        return ResponseEntity.status(200).body(new APIResponse("deleted successfully"));
    }

    @PutMapping("/assign-student-course/{studentId}/{courseId}")
    public ResponseEntity<?> assignStudentCourse(@PathVariable Integer studentId,@PathVariable Integer courseId){
        studentService.assignStudentCourse(studentId, courseId);

        return ResponseEntity.status(200).body(new APIResponse("assigned successfully"));
    }

    @PutMapping("/update-major/{studentId}/{major}")
    public ResponseEntity<?> updateMajor(@PathVariable Integer studentId,@PathVariable String major){
        studentService.updateMajor(studentId,major);
        return ResponseEntity.status(200).body(new APIResponse("major updated successfully"));
    }
}
