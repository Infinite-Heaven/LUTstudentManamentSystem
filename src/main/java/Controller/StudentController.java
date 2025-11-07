package Controller;

import Entity.Student;
import Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "*")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAll")
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/getById/{id}")
    public Student getStudentById(@PathVariable int id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/getByStudentNumber")
    public Student getStudentByStudentNumber(@RequestParam String studentNumber) {
        return studentService.getStudentByStudentNumber(studentNumber);
    }

    @GetMapping("/search")
    public List<Student> searchStudents(@RequestParam String name) {
        return studentService.searchStudentsByName(name);
    }

    @PostMapping("/add")
    public String addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return "学生添加成功";
        } catch (Exception e) {
            return "添加失败：" + e.getMessage();
        }
    }

    @PutMapping("/update")
    public String updateStudent(@RequestBody Student student) {
        try {
            studentService.updateStudent(student);
            return "学生信息更新成功";
        } catch (Exception e) {
            return "更新失败：" + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable int id) {
        try {
            studentService.deleteStudent(id);
            return "学生删除成功";
        } catch (Exception e) {
            return "删除失败：" + e.getMessage();
        }
    }
}

