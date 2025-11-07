package Service;

import Entity.Teacher;
import Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public List<Teacher> getAllTeachers() {
        Iterable<Teacher> teachers = teacherRepository.findAll();
        List<Teacher> teacherList = new ArrayList<>();
        for (Teacher teacher : teachers) {
            teacherList.add(teacher);
        }
        return teacherList;
    }

    public Teacher getTeacherById(int id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public Teacher getTeacherByTeacherNumber(String teacherNumber) {
        return teacherRepository.findByTeacherNumber(teacherNumber);
    }

    public Teacher getTeacherByUserId(int userId) {
        return teacherRepository.findByUserId(userId);
    }

    public List<Teacher> searchTeachersByName(String name) {
        return teacherRepository.findByNameContaining(name);
    }

    public Teacher addTeacher(Teacher teacher) {
        // 检查工号是否已存在
        if (teacher.getTeacherNumber() != null && 
            teacherRepository.findByTeacherNumber(teacher.getTeacherNumber()) != null) {
            throw new RuntimeException("工号已存在：" + teacher.getTeacherNumber());
        }
        return teacherRepository.save(teacher);
    }

    public Teacher updateTeacher(Teacher teacher) {
        if (teacherRepository.findById(teacher.getId()).isEmpty()) {
            throw new RuntimeException("教师不存在，ID：" + teacher.getId());
        }
        return teacherRepository.save(teacher);
    }

    public void deleteTeacher(int id) {
        if (teacherRepository.findById(id).isEmpty()) {
            throw new RuntimeException("教师不存在，ID：" + id);
        }
        teacherRepository.deleteById(id);
    }
}

