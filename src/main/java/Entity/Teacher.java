package Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "teacher")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_id")
    int id;
    
    @Column(name = "user_id")
    int userId;
    
    @Column(name = "teacher_number")
    String teacherNumber;
    
    String name;
}
