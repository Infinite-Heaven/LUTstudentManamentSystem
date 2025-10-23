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
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        if(userService.getUserByUsernameAndPassword(username, password) != null) {
            return "用户查询成功";
        }else {
            return null;
        }
    }

    //管理员管理用户接口（即删除已经存在的老师/学生 用户）
    @DeleteMapping("/delete/{username}")
    public String deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(userService.getUserByUsername(username));
        return "用户删除成功";

    }
}
