package Repository;

import Entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
User findUserByUsername(String username);
User findUserByUsernameAndPassword(String username, String password);
}

