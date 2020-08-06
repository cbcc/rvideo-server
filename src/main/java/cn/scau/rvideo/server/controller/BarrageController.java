package cn.scau.rvideo.server.controller;

import cn.scau.rvideo.server.auth.annotation.Token;
import cn.scau.rvideo.server.dto.Response;
import cn.scau.rvideo.server.dto.Status;
import cn.scau.rvideo.server.entity.Barrage;
import cn.scau.rvideo.server.service.BarrageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BarrageController {

    @Resource
    private BarrageService barrageService;

    @Token(roles = {"USER"})
    @PostMapping("/barrage")
    public Response save(@RequestBody Barrage barrage) {
        Response response = new Response();
        Integer result = barrageService.save(barrage);
        if (result == 1) {
            response.setStatus(Status.SUCCESS).setMessage("评论成功");
        } else {
            response.setStatus(Status.FAIL).setMessage("评论失败");
        }
        return response;
    }

    @GetMapping("/barrages/{videoId}")
    public Response findByVideoId(@PathVariable Integer videoId) {
        Response response = new Response();
        List<Barrage> barrageList = barrageService.findByVideoId(videoId);
        response.setData(barrageList).setStatus(Status.SUCCESS).setMessage("查找成功");
        return response;
    }
}
