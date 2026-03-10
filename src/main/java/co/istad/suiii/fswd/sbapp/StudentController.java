package co.istad.suiii.fswd.sbapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import static tools.jackson.databind.util.ClassUtil.name;
@RestController
public class StudentController{
    @GetMapping("/api/student")
    public List<Student> getStudents(){
        Student student = Student.builder()
                .name("Nhim Puthyseth")
                .gender("Male")
                .course("English")
                .build();
        Student student2 = Student.builder()
                .name("Suiiiii")
                .gender("Male")
                .course("Japanese")
                .build();
        return List.of(student,student2);

    }
}
