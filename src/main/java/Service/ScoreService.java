package Service;

import Entity.CourseSelection;
import Repository.CourseSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ScoreService {
    @Autowired
    CourseSelectRepository courseSelectRepository;

    public int getScore(int StudentId, int CourseId) {
        CourseSelection courseSelection = courseSelectRepository.findByStudentIdAndCourseId(StudentId, CourseId);
        if (courseSelection != null) {
            return courseSelection.getScore();
        }
        return -1;
    }
    
    public void addScore(int StudentId, int CourseId, int score) {
        CourseSelection courseSelection = courseSelectRepository.findByStudentIdAndCourseId(StudentId, CourseId);
        if (courseSelection != null) {
            courseSelection.setScore(score);
            courseSelectRepository.save(courseSelection);
        }
    }

    // 批量导入成绩
    // scores格式: [{"studentId": 1, "courseId": 1, "score": 85}, ...]
    public String batchImportScores(int courseId, List<Map<String, Object>> scores) {
        if (scores == null || scores.isEmpty()) {
            return "成绩列表为空";
        }
        
        int successCount = 0;
        int failCount = 0;
        StringBuilder errors = new StringBuilder();
        
        for (Map<String, Object> scoreData : scores) {
            try {
                Integer studentId = (Integer) scoreData.get("studentId");
                Integer courseIdFromData = (Integer) scoreData.get("courseId");
                Integer score = (Integer) scoreData.get("score");
                
                // 验证参数
                if (studentId == null || score == null) {
                    failCount++;
                    errors.append("缺少必要参数; ");
                    continue;
                }
                
                // 验证课程ID是否匹配
                if (courseIdFromData != null && !courseIdFromData.equals(courseId)) {
                    failCount++;
                    errors.append("课程ID不匹配; ");
                    continue;
                }
                
                // 验证成绩范围
                if (score < 0 || score > 100) {
                    failCount++;
                    errors.append("学生ID ").append(studentId).append(" 成绩超出范围; ");
                    continue;
                }
                
                // 检查选课记录是否存在
                CourseSelection courseSelection = courseSelectRepository.findByStudentIdAndCourseId(studentId, courseId);
                if (courseSelection == null) {
                    failCount++;
                    errors.append("学生ID ").append(studentId).append(" 未选该课程; ");
                    continue;
                }
                
                // 保存成绩
                courseSelection.setScore(score);
                courseSelectRepository.save(courseSelection);
                successCount++;
                
            } catch (Exception e) {
                failCount++;
                errors.append("处理失败: ").append(e.getMessage()).append("; ");
            }
        }
        
        return String.format("批量导入完成：成功 %d 条，失败 %d 条。%s", 
                successCount, failCount, errors.length() > 0 ? "错误信息：" + errors.toString() : "");
    }
}
