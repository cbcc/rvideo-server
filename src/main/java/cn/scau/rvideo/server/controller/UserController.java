package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.controller.validation.group.Register;
import cn.scau.rvideo.server.controller.validation.group.UpdateCustomFields;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
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
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private FileService<FileServiceImpl.FileType> fileService;

    @PostMapping("/user/register")
    public Response register(@RequestBody @Validated(value = {Register.class}) User user) {
        Response response = new Response();
        if (userService.isEmailExisted(user.getEmail())) {
            return response.setStatus(Status.FAIL).setMessage("该邮箱已被注册");
        }
        if (user.getSex() == null) {
            user.setSex(1);
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

    @Token(roles = {"ADMIN"})
    @DeleteMapping("/user/{id}")
    public Response delete(@PathVariable Integer id) {
        Response response = new Response();
        Integer result = userService.delete(id);
        if (result > 0) {
            response.setStatus(Status.SUCCESS).setMessage("用户删除成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("删除失败");
        }
        return response;
    }

    @Token(roles = {"USER", "ADMIN"})
    @GetMapping("/user/{id}")
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
    @PutMapping("/user/{id}")
    public Response updateCustomFields(@PathVariable Integer id, @RequestBody @Validated(value = {UpdateCustomFields.class}) User user) {
        Response response = new Response();
        if (userService.updateCustomFields(id, user.getName(), user.getSign(), user.getSex()) > 0) {
            response.setStatus(Status.SUCCESS).setMessage("更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PutMapping("/user/{id}/face")
    public Response updateFace(@PathVariable Integer id, @RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.USER_FACE);
        Response response = new Response();
        if (path != null && userService.updateFace(id, path) > 0) {
            response.setStatus(Status.SUCCESS).setMessage("更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
        }
        return response;
    }

    @Token(roles = {"ADMIN"})
    @GetMapping("/users")
    public Response getAll() {
        Response response = new Response();
        List<User> userList = userService.getAll();
        response.setData(userList).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }

    @GetMapping("/users/name/{name}")
    public Response findLikeName(@PathVariable String name) {
        Response response = new Response();
        List<User> userList = userService.findLikeName(name);
        response.setData(userList).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
