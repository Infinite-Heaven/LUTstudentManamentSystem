package Controller;

import Service.CourseSelectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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


}
