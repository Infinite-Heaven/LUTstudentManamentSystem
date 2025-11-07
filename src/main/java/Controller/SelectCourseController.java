package Controller;

import Entity.Course;
import Entity.CourseSelection;
import Service.CourseSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseSelect")
@CrossOrigin(origins = "*")
public class SelectCourseController {
    @Autowired
    CourseSelectService courseSelectService;

    @PutMapping("/selectOneCourse")
    public String selectCourse(@RequestParam Integer studentId, @RequestParam Integer courseId) {
        if (studentId != null && courseId != null) {
            courseSelectService.chooseOneCourse(studentId, courseId);
            return "选课成功";
        } else {
            return "参数错误：学生ID和课程ID不能为空";
        }
    }
    
    @DeleteMapping("/deleteCourseSelection/{student_id}/{course_id}")
    public String deleteCourseSelection(@PathVariable("student_id") Integer studentId, @PathVariable Integer courseId) {
        courseSelectService.deleteOneChosenCourse(studentId, courseId);
        return "课程删除完毕";
    }

    // 获取课程的所有选课学生列表
    @GetMapping("/getCourseStudents/{courseId}")
    public List<CourseSelection> getCourseStudents(@PathVariable Integer courseId) {
        return courseSelectService.getCourseStudents(courseId);
    }

    // 获取学生的所有选课列表
    @GetMapping("/getStudentCourses/{studentId}")
    public List<CourseSelection> getStudentCourses(@PathVariable Integer studentId) {
        return courseSelectService.getStudentCourses(studentId);
    }
}
