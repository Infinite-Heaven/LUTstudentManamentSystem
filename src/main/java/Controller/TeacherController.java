package Controller;

import Entity.Teacher;
import Service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
@CrossOrigin(origins = "*")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getAll")
    public List<Teacher> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping("/getById/{id}")
    public Teacher getTeacherById(@PathVariable int id) {
        return teacherService.getTeacherById(id);
    }

    @GetMapping("/getByTeacherNumber")
    public Teacher getTeacherByTeacherNumber(@RequestParam String teacherNumber) {
        return teacherService.getTeacherByTeacherNumber(teacherNumber);
    }

    @GetMapping("/getByUserId/{userId}")
    public Teacher getTeacherByUserId(@PathVariable int userId) {
        return teacherService.getTeacherByUserId(userId);
    }

    @GetMapping("/search")
    public List<Teacher> searchTeachers(@RequestParam String name) {
        return teacherService.searchTeachersByName(name);
    }

    @PostMapping("/add")
    public String addTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.addTeacher(teacher);
            return "教师添加成功";
        } catch (Exception e) {
            return "添加失败：" + e.getMessage();
        }
    }

    @PutMapping("/update")
    public String updateTeacher(@RequestBody Teacher teacher) {
        try {
            teacherService.updateTeacher(teacher);
            return "教师信息更新成功";
        } catch (Exception e) {
            return "更新失败：" + e.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteTeacher(@PathVariable int id) {
        try {
            teacherService.deleteTeacher(id);
            return "教师删除成功";
        } catch (Exception e) {
            return "删除失败：" + e.getMessage();
        }
    }
}

