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

}
