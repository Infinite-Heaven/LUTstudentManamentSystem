package Service;

import Entity.Course;
import Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        Iterable<Course> courses = courseRepository.findAll();
        List<Course> courseList = new ArrayList<>();
        for (Course course : courses) {
            courseList.add(course);
        }
        return courseList;
    }

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
