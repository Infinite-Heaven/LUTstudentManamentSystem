package Controller;

import Entity.Course;
import Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courseManagement")
@CrossOrigin(origins = "*")
public class CourseManagementController {
    @Autowired private CourseService courseService;

    @PostMapping("/addCourse")
    public String addCourse(@RequestBody Course course) {
        courseService.addCourse(course);
        return "课程增加成功";
    }


@PutMapping("updateCourse")
public String updateCourse(@RequestBody Course course) {
        courseService.updateCourse(course);
        return "课程修改成功";
}

    @DeleteMapping("/deleteCourse/{name}")
    public String deleteCourse(@PathVariable String name) {
        courseService.deleteCourseByName(name);
        return "课程删除成功";
    }


}
