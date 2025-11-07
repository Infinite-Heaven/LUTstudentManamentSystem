package Repository;

import Entity.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findByStudentNumber(String studentNumber);
    List<Student> findByNameContaining(String name);
}

