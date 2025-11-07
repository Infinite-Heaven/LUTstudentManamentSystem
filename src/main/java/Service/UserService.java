package Service;

import Entity.User;
import Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void addUser(User user) {
        // 检查用户名是否已存在
        if (userRepository.findUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("用户名已存在：" + user.getUsername());
        }
        userRepository.save(user);
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public User getUserByUsername(String username) {
        return  userRepository.findUserByUsername(username);
    }

    public User getUserByUsernameAndPassword(String username, String password) {
        return userRepository.findUserByUsernameAndPassword(username, password);
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public String updatePassword(String username, String oldPassword, String newPassword) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            return "用户不存在";
        }
        if (!user.getPassword().equals(oldPassword)) {
            return "原密码错误";
        }
        if (newPassword == null || newPassword.trim().isEmpty()) {
            return "新密码不能为空";
        }
        user.setPassword(newPassword);
        userRepository.save(user);
        return "密码修改成功";
    }
}
