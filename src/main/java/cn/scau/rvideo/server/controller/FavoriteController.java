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
    public Response save(@RequestBody Favorite favorite) {
        Response response = new Response();
        Integer result = favoriteService.save(favorite);
        if (result == 1) {
            response.setStatus(Status.SUCCESS).setMessage("收藏成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("收藏失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @GetMapping("/favorite/{userId}/{videoId}")
    public Response get(@PathVariable Integer userId, @PathVariable Integer videoId) {
        Response response = new Response();
        Favorite result = favoriteService.get(userId, videoId);
        if (result != null) {
            response.setStatus(Status.SUCCESS).setMessage("获取成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("获取失败");
        }
        return response;
    }

    @Token(roles = {"USER"})
    @DeleteMapping("/favorite/{userId}/{videoId}")
    public Response delete(@PathVariable Integer userId, @PathVariable Integer videoId) {
        Response response = new Response();
        Integer result = favoriteService.delete(userId, videoId);
        if (result == 1) {
            response.setStatus(Status.SUCCESS).setMessage("取消成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("取消失败");
        }
        return response;
    }

    @GetMapping("/favorites/{userId}")
    public Response findVideoByUserId(@PathVariable Integer userId) {
        Response response = new Response();
        List<Integer> videoIdList = favoriteService.findVideoIdByUserId(userId);
        if (!videoIdList.isEmpty()) {
            List<Video> videoList = videoService.getIn(videoIdList);
            response.setData(videoList);
        }
        response.setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
