package Controller;

import Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scoreManagement")
@CrossOrigin(origins = "*")
public class ScoreManagementController {
    @Autowired
    ScoreService scoreService;

    @PostMapping("/addScore")
    public String addScore(@RequestParam int StudentId, @RequestParam int CourseId, @RequestParam int score) {
        if (score >= 0 && score <= 100) {
            scoreService.addScore(StudentId, CourseId, score);
            return "成绩写入完成";
        } else {
            return "成绩必须在0-100之间";
        }
    }
    @GetMapping("/getScore")
    public String getScore(@RequestParam int StudentId, @RequestParam int CourseId) {
        int score = scoreService.getScore(StudentId, CourseId);
        if (score == -1) {
            return "未找到对应的成绩记录";
        }
        return "成绩：" + score;
    }

}
