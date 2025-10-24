package Controller;

import Service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scoreManagement")
@CrossOrigin(origins = "*")
public class ScoreManagementController {
    @Autowired
    ScoreService scoreService;

    public String addScore(int StudentId, int CourseId, int score) {
        scoreService.addScore(StudentId, CourseId, score);
        return "成绩写入完成";
    }

    public int getScore(int StudentId, int CourseId) {
   return scoreService.getScore(StudentId, CourseId);
    }

}
