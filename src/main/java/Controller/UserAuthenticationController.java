package Controller;

import Entity.User;
import Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController //这个类所输出的对象，以json格式输出给前端
@RequestMapping("/user")
@CrossOrigin(origins = "*") // 允许跨域访问
public class UserAuthenticationController {
    @Autowired
    UserService userService;

    //用户注册接口
    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.addUser(user);
        return "用户添加成功";
    }


    //用户登录接口
    @GetMapping("/login")
    public User login(@RequestParam("username") String username, @RequestParam("password") String password) {
        User user = userService.getUserByUsernameAndPassword(username, password);
        if(user != null) {
            // 不返回密码
            user.setPassword(null);
            return user;
        }else {
            return null;
        }
    }

    //管理员管理用户接口（即删除已经存在的老师/学生 用户）
    @DeleteMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            userService.deleteUser(user);
            return "用户删除成功";
        } else {
            return "用户不存在";
        }
    }

    //修改密码接口
    @PutMapping("/updatePassword")
    public String updatePassword(@RequestParam String username, 
                                 @RequestParam String oldPassword, 
                                 @RequestParam String newPassword) {
        return userService.updatePassword(username, oldPassword, newPassword);
    }

    //根据用户名获取用户信息（用于管理员搜索）
    @GetMapping("/getByUsername")
    public User getUserByUsername(@RequestParam String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            // 不返回密码
            user.setPassword(null);
        }
        return user;
    }
}
