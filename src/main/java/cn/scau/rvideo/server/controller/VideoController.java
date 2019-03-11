package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.controller.validation.group.Submit;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
import cn.scau.rvideo.server.entity.Video;
import cn.scau.rvideo.server.service.FileService;
import cn.scau.rvideo.server.service.VideoService;
import cn.scau.rvideo.server.service.impl.FileServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class VideoController {

    @Resource
    private VideoService videoService;
    @Resource
    private FileService<FileServiceImpl.FileType> fileService;

    @Token(roles = {"USER"})
    @PostMapping("/video/file/upload")
    public Response uploadFile(@RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.VIDEO_FILE);
        Response response = new Response();
        if (path != null) {
            System.out.println(path);
            response.setData(path).setStatus(Status.SUCCESS).setMessage("上传成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("上传失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PostMapping("/video/face/upload")
    public Response uploadFace(@RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.VIDEO_FACE);
        Response response = new Response();
        if (path != null) {
            response.setData(path).setStatus(Status.SUCCESS).setMessage("上传成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("上传失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @PostMapping("/video/submit")
    public Response submit(@RequestBody @Validated(value = {Submit.class}) Video video) {
        Response response = new Response();
        video = videoService.save(video);
        if (video != null) {
            response.setStatus(Status.SUCCESS).setMessage("投稿成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("投稿失败");
        }
        return response;
    }

    @GetMapping("/video/{id}")
    public Response get(@PathVariable Integer id) {
        Response response = new Response();
        Video video = videoService.get(id);
        if (video != null) {
            response.setData(video).setStatus(Status.SUCCESS).setMessage("查找成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("查找失败");
        }
        return response;
    }

    @GetMapping("/videos")
    public Response find(@RequestParam(name = "user-id", required = false) Integer userId, @RequestParam(required = false) String name) {
        Response response = new Response();
        List<Video> videos;
        if (userId != null) {
            videos = videoService.findByUserId(userId);
            response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找成功");
        } else if (name != null) {
            videos = videoService.findLikeName(name);
            response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找成功");
        } else {
            response.setStatus(Status.ARGUMENT_NOT_VALID).setMessage("参数错误");
        }
        return response;
    }

    @GetMapping("/videos/{tag}")
    public Response findByTag(@PathVariable String tag) {
        Response response = new Response();
        List<Video> videos = videoService.findByTag(tag);
        response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
