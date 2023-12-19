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
    public Response<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.VIDEO_FILE);
        if (path != null) {
            return Response.success(path, "上传成功");
        } else {
            return Response.fail("上传失败");
        }
    }

    @Token(roles = {"USER"})
    @PostMapping("/video/face/upload")
    public Response<String> uploadFace(@RequestParam("file") MultipartFile file) {
        String path = fileService.save(file, FileServiceImpl.FileType.VIDEO_FACE);
        if (path != null) {
            return Response.success(path, "上传成功");
        } else {
            return Response.fail("上传失败");
        }
    }

    @Token(roles = {"USER"})
    @PostMapping("/video/submit")
    public Response<Void> submit(@RequestBody @Validated(value = {Submit.class}) Video video) {
        video = videoService.save(video);
        if (video != null) {
            return Response.success("投稿成功");
        } else {
            return Response.fail("投稿失败");
        }
    }

    @GetMapping("/video/{id}")
    public Response<Video> get(@PathVariable Integer id) {
        Video video = videoService.get(id);
        if (video != null) {
            return Response.success(video, "查找成功");
        } else {
            return Response.fail("查找失败");
        }
    }

    @Token(roles = {"ADMIN"})
    @GetMapping("/videos/all")
    public Response<List<Video>> getAll() {
        List<Video> videos = videoService.getAll();
        return Response.success(videos, "查找所有成功");
    }

    @Token(roles = {"ADMIN"})
    @DeleteMapping("/video/{id}")
    public Response<Void> delete(@PathVariable Integer id) {
        Integer result = videoService.delete(id);
        if (result > 0) {
            return Response.success("删除成功");
        } else {
            return Response.fail("删除失败");
        }
    }

    @Token(roles = {"USER", "ADMIN"})
    @PutMapping("/video/{id}/likes")
    public Response<Void> updateLikes(@PathVariable Integer id, @RequestBody Video video) {
        Integer result = videoService.updateLikes(id, video.getLikes());
        if (result > 0) {
            return Response.success("点赞量更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @PutMapping("/video/{id}/views")
    public Response<Void> updateViews(@PathVariable Integer id, @RequestBody Video video) {
        Integer result = videoService.updateViews(id, video.getViews());
        if (result > 0) {
            return Response.success("观看量更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @Token(roles = {"ADMIN"})
    @PutMapping("/video/{id}/verify")
    public Response<Void> updateVerify(@PathVariable Integer id, @RequestBody Video video) {
        Integer result = videoService.updateVerify(id, video.getVerify());
        if (result > 0) {
            return Response.success("审批更新成功");
        } else {
            return Response.fail("更新失败");
        }
    }

    @GetMapping("/videos")
    public Response<List<Video>> find(@RequestParam(name = "user-id", required = false) Integer userId, @RequestParam(required = false) String name) {
        List<Video> videos;
        if (userId != null) {
            videos = videoService.findByUserId(userId);
            return Response.success(videos, "查找成功");
        } else if (name != null) {
            videos = videoService.findLikeName(name);
            return Response.success(videos, "查找成功");
        } else {
            return new Response<List<Video>>().setStatus(Status.ARGUMENT_NOT_VALID).setMessage("参数错误");
        }
    }

    @GetMapping("/videos/tag/{tag}")
    public Response<List<Video>> findByTag(@PathVariable String tag) {
        List<Video> videos = videoService.findByTag(tag);
        return Response.success(videos, "查找成功");
    }

    @GetMapping("/videos/verify/{verify}")
    public Response<List<Video>> findByVerify(@PathVariable Integer verify) {
        List<Video> videos = videoService.findByVerify(verify);
        return Response.success(videos, "查找成功");
    }
}
