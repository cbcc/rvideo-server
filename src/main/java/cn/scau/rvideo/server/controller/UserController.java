package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.controller.validation.group.Register;
import cn.scau.rvideo.server.controller.validation.group.UpdateCustomFields;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.service.FileService;
import cn.scau.rvideo.server.service.UserService;
import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.entity.User;
import cn.scau.rvideo.server.service.impl.FileServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private FileService<FileServiceImpl.FileType> fileService;

    @PostMapping("/user/register")
    public Response<Void> register(@RequestBody @Validated(value = {Register.class}) User user) {
        if (userService.isEmailExisted(user.getEmail())) {
            return Response.fail("该邮箱已被注册");
        }
        if (user.getSex() == null) {
            user.setSex(1);
        }
        user.setRoles("USER");
        user = userService.register(user);
        if (user != null) {
            return Response.success("注册成功");
        } else {
            return Response.fail("注册失败");
        }
    }

    @Token(roles = {"ADMIN"})
    @DeleteMapping("/user/{id}")
    public Response<Void> delete(@PathVariable Integer id) {
        Integer result = userService.delete(id);
        if (result > 0) {
            return Response.success("删除成功");
        } else {
            return Response.fail("删除失败");
        }
    }

    @Token(roles = {"USER", "ADMIN"})
    @GetMapping("/user/{id}")
    public Response<User> getDetail(@PathVariable Integer id) {
        User user = userService.getDetail(id);
        if (user != null) {
            return Response.success(user, "获取成功");
        } else {
            return Response.fail("获取失败");
        }
    }

    @Token(roles = {"USER"})
    @PutMapping("/user/{id}")
    public Response<Void> updateCustomFields(@PathVariable Integer id, @RequestBody @Validated(value = {UpdateCustomFields.class}) User user) {
        if (userService.updateCustomFields(id, user.getName(), user.getSign(), user.getSex()) > 0) {
            return Response.success("更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @Token(roles = {"USER"})
    @PutMapping("/user/{id}/face")
    public Response<Void> updateFace(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.USER_FACE);
        if (path != null && userService.updateFace(id, path) > 0) {
            return Response.success("更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @Token(roles = {"ADMIN"})
    @GetMapping("/users")
    public Response<List<User>> getAll() {
        List<User> userList = userService.getAll();
        return Response.success(userList, "查找成功");
    }

    @GetMapping("/users/name/{name}")
    public Response<List<User>> findLikeName(@PathVariable String name) {
        List<User> userList = userService.findLikeName(name);
        return Response.success(userList, "查找成功");
    }
}
