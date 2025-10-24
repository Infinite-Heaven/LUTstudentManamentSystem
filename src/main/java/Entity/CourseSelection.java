package Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "course_selection")
public class CourseSelection {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "student_id")
    int studentId;
    @Column(name = "course_id")
    int courseId;

    int score;

}
