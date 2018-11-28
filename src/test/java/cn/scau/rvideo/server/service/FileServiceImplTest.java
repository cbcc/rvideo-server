package cn.scau.rvideo.server.service;

import cn.scau.rvideo.server.service.impl.FileServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceImplTest {

    @Resource
    private FileService<FileServiceImpl.FileType> fileService;

    @Test
    public void testCreateSavePath() {
        byte[] bytes = {1,2};
        String path = fileService.save(new MockMultipartFile("a", bytes), FileServiceImpl.FileType.USER_FACE);
        System.out.println(path);
    }
}
