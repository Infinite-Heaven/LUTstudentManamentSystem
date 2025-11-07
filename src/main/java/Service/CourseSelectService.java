package Service;

import Entity.CourseSelection;
import Repository.CourseSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseSelectService {
    @Autowired
    private CourseSelectRepository courseSelectRepository;

    public void chooseOneCourse(Integer studentId, Integer courseId) {
        CourseSelection courseSelection = new CourseSelection();
        courseSelection.setCourseId(courseId);
        courseSelection.setStudentId(studentId);
        courseSelectRepository.save(courseSelection);

    }

    public void deleteOneChosenCourse(Integer studentId, Integer courseId) {


        courseSelectRepository.deleteCourseSelectionByStudentIdAndCourseId(studentId, courseId);

    }
    public Double getAverageScoreForStudent(int studentId) {
        int totalScore = 0;
        int count = 0;


        List<CourseSelection> courseList = courseSelectRepository.findByStudentId(studentId);


        for (CourseSelection courseSelection : courseList) {
            totalScore += courseSelection.getScore();
            count++;
        }


        if (count == 0) {
            return null;
        }

        // 计算平均分，确保类型转换为 Double
        return totalScore == 0 ? 0.0 : (double) totalScore / count;
    }

    public Double getAverageScoreForCourse(int courseId) {
        int totalScore = 0;
        int count = 0;
        List<CourseSelection> courseList = courseSelectRepository.findByCourseId(courseId);
        for (CourseSelection courseSelection : courseList) {
            totalScore += courseSelection.getScore();
            count++;
        }
        if (count == 0) {
            return null;
        }
        return totalScore == 0 ? 0.0 : (double) totalScore / count;

    }
    public Double getCreditScoreForStudent(int studentId) {
        Double totalCreditScore = 0.0;
        List<CourseSelection> courseList = courseSelectRepository.findByStudentId(studentId);
        for (CourseSelection courseSelection : courseList) {
            totalCreditScore += courseSelection.getScore();
        }
        return totalCreditScore;
    }

    // 获取课程最高分
    public Integer getMaxScoreForCourse(int courseId) {
        List<CourseSelection> courseList = courseSelectRepository.findByCourseId(courseId);
        if (courseList == null || courseList.isEmpty()) {
            return null;
        }
        int maxScore = -1;
        for (CourseSelection cs : courseList) {
            if (cs.getScore() > maxScore && cs.getScore() >= 0) {
                maxScore = cs.getScore();
            }
        }
        return maxScore == -1 ? null : maxScore;
    }

    // 获取课程最低分
    public Integer getMinScoreForCourse(int courseId) {
        List<CourseSelection> courseList = courseSelectRepository.findByCourseId(courseId);
        if (courseList == null || courseList.isEmpty()) {
            return null;
        }
        int minScore = 101;
        for (CourseSelection cs : courseList) {
            if (cs.getScore() >= 0 && cs.getScore() < minScore) {
                minScore = cs.getScore();
            }
        }
        return minScore == 101 ? null : minScore;
    }

    // 获取学生最高分
    public Integer getMaxScoreForStudent(int studentId) {
        List<CourseSelection> courseList = courseSelectRepository.findByStudentId(studentId);
        if (courseList == null || courseList.isEmpty()) {
            return null;
        }
        int maxScore = -1;
        for (CourseSelection cs : courseList) {
            if (cs.getScore() > maxScore && cs.getScore() >= 0) {
                maxScore = cs.getScore();
            }
        }
        return maxScore == -1 ? null : maxScore;
    }

    // 获取学生最低分
    public Integer getMinScoreForStudent(int studentId) {
        List<CourseSelection> courseList = courseSelectRepository.findByStudentId(studentId);
        if (courseList == null || courseList.isEmpty()) {
            return null;
        }
        int minScore = 101;
        for (CourseSelection cs : courseList) {
            if (cs.getScore() >= 0 && cs.getScore() < minScore) {
                minScore = cs.getScore();
            }
        }
        return minScore == 101 ? null : minScore;
    }

    // 获取课程成绩统计（平均分、最高分、最低分、总人数）
    public java.util.Map<String, Object> getCourseStatistics(int courseId) {
        List<CourseSelection> courseList = courseSelectRepository.findByCourseId(courseId);
        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        
        if (courseList == null || courseList.isEmpty()) {
            stats.put("average", null);
            stats.put("max", null);
            stats.put("min", null);
            stats.put("count", 0);
            return stats;
        }
        
        int totalScore = 0;
        int count = 0;
        int maxScore = -1;
        int minScore = 101;
        
        for (CourseSelection cs : courseList) {
            if (cs.getScore() >= 0) {
                totalScore += cs.getScore();
                count++;
                if (cs.getScore() > maxScore) {
                    maxScore = cs.getScore();
                }
                if (cs.getScore() < minScore) {
                    minScore = cs.getScore();
                }
            }
        }
        
        stats.put("average", count > 0 ? (double) totalScore / count : null);
        stats.put("max", maxScore == -1 ? null : maxScore);
        stats.put("min", minScore == 101 ? null : minScore);
        stats.put("count", count);
        
        return stats;
    }

    // 获取学生成绩统计
    public java.util.Map<String, Object> getStudentStatistics(int studentId) {
        List<CourseSelection> courseList = courseSelectRepository.findByStudentId(studentId);
        java.util.Map<String, Object> stats = new java.util.HashMap<>();
        
        if (courseList == null || courseList.isEmpty()) {
            stats.put("average", null);
            stats.put("max", null);
            stats.put("min", null);
            stats.put("count", 0);
            return stats;
        }
        
        int totalScore = 0;
        int count = 0;
        int maxScore = -1;
        int minScore = 101;
        
        for (CourseSelection cs : courseList) {
            if (cs.getScore() >= 0) {
                totalScore += cs.getScore();
                count++;
                if (cs.getScore() > maxScore) {
                    maxScore = cs.getScore();
                }
                if (cs.getScore() < minScore) {
                    minScore = cs.getScore();
                }
            }
        }
        
        stats.put("average", count > 0 ? (double) totalScore / count : null);
        stats.put("max", maxScore == -1 ? null : maxScore);
        stats.put("min", minScore == 101 ? null : minScore);
        stats.put("count", count);
        
        return stats;
    }

    // 获取课程的所有选课学生列表
    public List<CourseSelection> getCourseStudents(int courseId) {
        return courseSelectRepository.findByCourseId(courseId);
    }

    // 获取学生的所有选课列表
    public List<CourseSelection> getStudentCourses(int studentId) {
        return courseSelectRepository.findByStudentId(studentId);
    }

}
