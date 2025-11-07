package Service;

import Entity.Student;
import Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        Iterable<Student> students = studentRepository.findAll();
        List<Student> studentList = new ArrayList<>();
        for (Student student : students) {
            studentList.add(student);
        }
        return studentList;
    }

    public Student getStudentById(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByStudentNumber(String studentNumber) {
        return studentRepository.findByStudentNumber(studentNumber);
    }

    public List<Student> searchStudentsByName(String name) {
        return studentRepository.findByNameContaining(name);
    }

    public Student addStudent(Student student) {
        // 检查学号是否已存在
        if (student.getStudentNumber() != null && 
            studentRepository.findByStudentNumber(student.getStudentNumber()) != null) {
            throw new RuntimeException("学号已存在：" + student.getStudentNumber());
        }
        return studentRepository.save(student);
    }

    public Student updateStudent(Student student) {
        if (studentRepository.findById(student.getId()).isEmpty()) {
            throw new RuntimeException("学生不存在，ID：" + student.getId());
        }
        return studentRepository.save(student);
    }

    public void deleteStudent(int id) {
        if (studentRepository.findById(id).isEmpty()) {
            throw new RuntimeException("学生不存在，ID：" + id);
        }
        studentRepository.deleteById(id);
    }
}

