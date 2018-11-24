package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.dto.Response;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/error")
@CrossOrigin(origins = "http://localhost:4200")
public class ErrorController {

    @PostMapping("/402")
    public Response AuthError() {
        return new Response().setCode(402).setMsg("没有权限");
    }
}
