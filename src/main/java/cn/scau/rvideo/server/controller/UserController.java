package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.auth.service.JwtService;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Response register(@RequestBody  User user){
        user = userService.register(user);
        Response response = new Response();
        if(user != null){
            response.setData(user).setCode(1).setMsg("注册成功");
        } else {
            response.setCode(-1).setMsg("注册失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @GetMapping("/{id}")
    public Response getDetail(@PathVariable Integer id) {
        Response response = new Response();
        User user = userService.getDetail(id);
        if (user != null) {
            response.setData(user).setCode(1).setMsg("获取成功");
        } else {
            response.setData(-1).setMsg("获取失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PutMapping("/{id}")
    public Response updateCustomFields(@PathVariable Integer id, @RequestBody User user) {
        Response response = new Response();
        if(userService.updateCustomFields(id, user.getName(), user.getSign(), Integer.parseInt(user.getSex())) > 0) {
            response.setCode(1).setMsg("更新成功");
        } else {
            response.setCode(-1).setMsg("更新失败");
        }
        return response;
    }

}
