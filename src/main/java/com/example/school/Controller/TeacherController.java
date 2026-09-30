package com.example.school.Controller;

import com.example.school.API.APIResponse;
import com.example.school.Model.Teacher;
import com.example.school.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping("/get")
    public ResponseEntity<?> get(){
        return ResponseEntity.status(200).body(teacherService.get());
    }

    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody @Valid Teacher teacher){
        teacherService.add(teacher);
        return ResponseEntity.status(200).body(new APIResponse("added successfully"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,@RequestBody @Valid Teacher teacher){
        teacherService.update(id,teacher);
        return ResponseEntity.status(200).body(new APIResponse("updated successfully"));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        teacherService.delete(id);
        return ResponseEntity.status(200).body(new APIResponse("deleted successfully"));
    }

    @GetMapping("/get-details/{id}")
    public ResponseEntity<?> getDetails(@PathVariable Integer id){
        return ResponseEntity.status(200).body(teacherService.getDetails(id));
    }

}
