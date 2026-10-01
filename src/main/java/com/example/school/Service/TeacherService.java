package com.example.school.Service;

import com.example.school.API.APIException;
import com.example.school.Model.Address;
import com.example.school.Model.Course;
import com.example.school.Model.Teacher;
import com.example.school.Repository.CourseRepository;
import com.example.school.Repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRepository teacherRepository;
//    private final CourseRepository courseRepository;

    public List<Teacher> get(){
        return teacherRepository.findAll();
    }

    public void add(Teacher teacher){
        if (teacher.getAddress() != null)
            throw new APIException("teacher already has an address");
        teacherRepository.save(teacher);
    }

    public void update(Integer id, Teacher teacher){
        Teacher oldTeacher = teacherRepository.findTeacherById(id);
        if (oldTeacher == null)
            throw new APIException("teacher not exist");

//        oldTeacher.setAddress(teacher.getAddress());
        oldTeacher.setAge(teacher.getAge());
        oldTeacher.setEmail(teacher.getEmail());
        oldTeacher.setName(teacher.getName());
        oldTeacher.setSalary(teacher.getSalary());

        teacherRepository.save(oldTeacher);
    }

    public void delete(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
            throw new APIException("teacher not exist");

        teacherRepository.delete(teacher);

    }

    public String getDetails(Integer id){
        Teacher teacher = teacherRepository.findTeacherById(id);
        if (teacher == null)
            throw new APIException("teacher not exist");

        Address address = teacher.getAddress();
        String s = "";

        s += "Email: " + teacher.getEmail() + "\n";
        s += "name: " + teacher.getName() + "\n";
        s += "age: " + teacher.getAge() + "\n";
        s += "salary: " + teacher.getSalary() + "\n";
        if (address != null) {
            s += "area: " + address.getArea() + "\n";
            s += "street: " + address.getStreet() + "\n";
            s += "building number: " + address.getBuildingNumber() + "\n";
        }
        if (!teacher.getCourses().isEmpty())
            s += "his courses: " + "\n";
        for (Course course: teacher.getCourses()){
            s +=  course.getName() + "\n";
        }
        return s;
    }


}
