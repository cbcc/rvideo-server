package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.entity.Barrage;
import cn.scau.rvideo.server.service.BarrageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BarrageController {

    @Resource
    private BarrageService barrageService;

    @Token(roles = {"USER"})
    @PostMapping("/barrage")
    public Response<Void> save(@RequestBody Barrage barrage) {
        Integer result = barrageService.save(barrage);
        if (result == 1) {
            return Response.success("评论成功");
        } else {
            return Response.fail("评论失败");
        }
    }

    @GetMapping("/barrages/{videoId}")
    public Response<List<Barrage>> findByVideoId(@PathVariable Integer videoId) {
        List<Barrage> barrageList = barrageService.findByVideoId(videoId);
        return Response.success(barrageList, "查找成功");
    }
}
