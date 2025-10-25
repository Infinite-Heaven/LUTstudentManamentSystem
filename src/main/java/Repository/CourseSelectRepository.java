package Repository;

import Entity.Course;
import Entity.CourseSelection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseSelectRepository extends CrudRepository<CourseSelection, Integer> {
CourseSelection findByStudentIdAndCourseId(Integer studentId, Integer courseId);
void deleteCourseSelectionByStudentIdAndCourseId(Integer studentId, Integer courseId);
List<CourseSelection> findByStudentId(Integer studentId);
List<CourseSelection> findByCourseId(Integer courseId);
}
