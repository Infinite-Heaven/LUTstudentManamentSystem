package Service;

import Entity.CourseSelection;
import Repository.CourseSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
