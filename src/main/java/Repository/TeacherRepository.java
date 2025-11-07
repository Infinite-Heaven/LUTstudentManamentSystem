package Repository;

import Entity.Teacher;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
    Teacher findByTeacherNumber(String teacherNumber);
    Teacher findByUserId(Integer userId);
    List<Teacher> findByNameContaining(String name);
}

