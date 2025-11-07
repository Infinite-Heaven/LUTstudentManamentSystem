package Controller;

import Service.CourseSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statisticsMessage")
@CrossOrigin(origins = "*")
public class StatisticsMessageController {

    @Autowired
    CourseSelectService courseSelectService;

    //查询学生的所有课程平均分
    @GetMapping("/studentAverageScore")
    public Double studentAverageScore(@RequestParam int studentId) {
        return courseSelectService.getAverageScoreForStudent(studentId);
    }
    
    //查询课程的所有学生平均分
    @GetMapping("/courseAverageScore")
    public Double courseAverageScore(@RequestParam int courseId) {
        return courseSelectService.getAverageScoreForCourse(courseId);
    }

    //查询学生当前选课所有绩点
    @GetMapping("/studentCredit")
    public Double studentCredit(@RequestParam int studentId) {
        return courseSelectService.getCreditScoreForStudent(studentId);
    }

    //查询课程最高分
    @GetMapping("/courseMaxScore")
    public Integer courseMaxScore(@RequestParam int courseId) {
        return courseSelectService.getMaxScoreForCourse(courseId);
    }

    //查询课程最低分
    @GetMapping("/courseMinScore")
    public Integer courseMinScore(@RequestParam int courseId) {
        return courseSelectService.getMinScoreForCourse(courseId);
    }

    //查询学生最高分
    @GetMapping("/studentMaxScore")
    public Integer studentMaxScore(@RequestParam int studentId) {
        return courseSelectService.getMaxScoreForStudent(studentId);
    }

    //查询学生最低分
    @GetMapping("/studentMinScore")
    public Integer studentMinScore(@RequestParam int studentId) {
        return courseSelectService.getMinScoreForStudent(studentId);
    }

    //获取课程完整统计信息（平均分、最高分、最低分、总人数）
    @GetMapping("/courseStatistics")
    public Map<String, Object> courseStatistics(@RequestParam int courseId) {
        return courseSelectService.getCourseStatistics(courseId);
    }

    //获取学生完整统计信息（平均分、最高分、最低分、课程数）
    @GetMapping("/studentStatistics")
    public Map<String, Object> studentStatistics(@RequestParam int studentId) {
        return courseSelectService.getStudentStatistics(studentId);
    }
}
