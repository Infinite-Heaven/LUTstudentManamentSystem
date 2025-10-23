package Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    int id;

    String studentNumber;
    String name;

    //暂时弃用变量
    //  int userId;
    //  int classId;
}
