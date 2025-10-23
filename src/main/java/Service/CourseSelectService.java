package Service;

import Entity.CourseSelection;
import Repository.CourseSelectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
