package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
import cn.scau.rvideo.server.entity.Favorite;
import cn.scau.rvideo.server.entity.Video;
import cn.scau.rvideo.server.service.FavoriteService;
import cn.scau.rvideo.server.service.VideoService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FavoriteController {

    @Resource
    private FavoriteService favoriteService;
    @Resource
    private VideoService videoService;

    @Token(roles = {"USER"})
    @PostMapping("/favorite")
    public Response<Void> save(@RequestBody Favorite favorite) {
        Integer result = favoriteService.save(favorite);
        if (result == 1) {
            return Response.success("收藏成功");
        } else {
            return Response.fail("收藏失败");
        }
    }

    @Token(roles = {"USER"})
    @GetMapping("/favorite/{userId}/{videoId}")
    public Response<Void> get(@PathVariable Integer userId, @PathVariable Integer videoId) {
        Favorite result = favoriteService.get(userId, videoId);
        if (result != null) {
            return Response.success("获取成功");
        } else {
            return Response.fail("获取失败");
        }
    }

    @Token(roles = {"USER"})
    @DeleteMapping("/favorite/{userId}/{videoId}")
    public Response<Void> delete(@PathVariable Integer userId, @PathVariable Integer videoId) {
        Integer result = favoriteService.delete(userId, videoId);
        if (result == 1) {
            return Response.success("取消成功");
        } else {
            return Response.fail("取消失败");
        }
    }

    @GetMapping("/favorites/{userId}")
    public Response<List<Video>> findVideoByUserId(@PathVariable Integer userId) {
        Response<List<Video>> response = new Response<>();
        List<Integer> videoIdList = favoriteService.findVideoIdByUserId(userId);
        if (!videoIdList.isEmpty()) {
            List<Video> videoList = videoService.getIn(videoIdList);
            response.setData(videoList);
        }
        response.setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
