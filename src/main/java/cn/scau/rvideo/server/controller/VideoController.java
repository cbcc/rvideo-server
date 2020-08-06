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

    @Token(roles = {"ADMIN"})
    @GetMapping("/videos/all")
    public Response getAll() {
        Response response = new Response();
        List<Video> videos = videoService.getAll();
        response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找所有成功");
        return response;
    }

    @Token(roles = {"ADMIN"})
    @DeleteMapping("/video/{id}")
    public Response delete(@PathVariable Integer id) {
        Response response = new Response();
        Integer result = videoService.delete(id);
        if (result > 0) {
            response.setStatus(Status.SUCCESS).setMessage("删除成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("删除失败");
        }
        return response;
    }

    @Token(roles = {"USER", "ADMIN"})
    @PutMapping("/video/{id}/likes")
    public Response updateLikes(@PathVariable Integer id, @RequestBody Video video) {
        Response response = new Response();
        Integer result = videoService.updateLikes(id, video.getLikes());
        if (result > 0) {
            response.setStatus(Status.SUCCESS).setMessage("点赞量更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("跟新失败");
        }
        return response;
    }

    @PutMapping("/video/{id}/views")
    public Response updateViews(@PathVariable Integer id, @RequestBody Video video) {
        Response response = new Response();
        Integer result = videoService.updateViews(id, video.getViews());
        if (result > 0) {
            response.setStatus(Status.SUCCESS).setMessage("观看量更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
        }
        return response;
    }

    @Token(roles = {"ADMIN"})
    @PutMapping("/video/{id}/verify")
    public Response updateVerify(@PathVariable Integer id, @RequestBody Video video) {
        Response response = new Response();
        Integer result = videoService.updateVerify(id, video.getVerify());
        if (result > 0) {
            response.setStatus(Status.SUCCESS).setMessage("审批更新成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("更新失败");
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

    @GetMapping("/videos/tag/{tag}")
    public Response findByTag(@PathVariable String tag) {
        Response response = new Response();
        List<Video> videos = videoService.findByTag(tag);
        response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }

    @GetMapping("/videos/verify/{verify}")
    public Response findByVerify(@PathVariable Integer verify) {
        Response response = new Response();
        List<Video> videos = videoService.findByVerify(verify);
        response.setData(videos).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
