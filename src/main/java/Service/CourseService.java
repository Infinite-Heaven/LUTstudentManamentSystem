package Service;

import Entity.Course;
import Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

public void addCourse(Course course) {
    courseRepository.save(course);
}

public void deleteCourseByName(String name) {
    courseRepository.deleteByName(name);
}

public void updateCourse(Course course) {
    courseRepository.save(course);
}



}
