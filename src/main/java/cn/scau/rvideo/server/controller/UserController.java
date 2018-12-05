package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
import cn.scau.rvideo.server.service.FileService;
import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.entity.User;
import cn.scau.rvideo.server.service.impl.FileServiceImpl;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private FileService<FileServiceImpl.FileType> fileService;

    @PostMapping("/register")
    public Response register(@RequestBody User user) {
        Response response = new Response();
        if (userService.isEmailExisted(user.getEmail())) {
            return response.setStatus(Status.FAIL).setMessage("该邮箱已被注册");
        }
        if (user.getSex() == null) {
            user.setSex("1");
        }
        user.setRoles("USER");
        user = userService.register(user);
        if (user != null) {
            response.setStatus(Status.SUCCESS).setMessage("注册成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("注册失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @GetMapping("/{id}")
    public Response getDetail(@PathVariable Integer id) {
        Response response = new Response();
        User user = userService.getDetail(id);
        if (user != null) {
            response.setData(user).setStatus(Status.SUCCESS).setMessage("获取成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("获取失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PutMapping("/{id}")
    public Response updateCustomFields(@PathVariable Integer id, @RequestBody User user) {
        Response response = new Response();
        if (userService.updateCustomFields(id, user.getName(), user.getSign(), Integer.parseInt(user.getSex())) > 0) {
            response.setStatus(Status.SUCCESS).setMessage("更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PutMapping("{id}/face")
    public Response updateFace(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.USER_FACE);
        Response response = new Response();
        if (path != null && userService.updateFace(id, path) > 0) {
            response.setData("1").setStatus(Status.SUCCESS).setMessage("更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
        }
        return response;
    }
}
