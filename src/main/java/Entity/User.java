package Entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "user")
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
            @Column(name = "user_id")
    int id;
    String username;
    String password;
    String role;
}
