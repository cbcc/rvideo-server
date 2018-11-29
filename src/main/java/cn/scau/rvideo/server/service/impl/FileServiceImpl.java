package cn.scau.rvideo.server.service.impl;

import cn.scau.rvideo.server.config.ResourceProperties;
import cn.scau.rvideo.server.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * 提供保存文件服务
 *
 * @author cbc
 */
@Service
public class FileServiceImpl implements FileService<FileServiceImpl.FileType> {
    private static final String USER_FACE_PATH = "/user/face";
    private static final String VIDEO_FACE_PATH = "/video/face";
    private static final String VIDEO_FILE_PATH = "/video";
    @Resource
    private ResourceProperties resourceProperties;
    private static final Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    /**
     * USER_FACE: 用户头像
     * VIDEO_FILE: 视频文件
     * VIDEO_FACE: 视频封面
     */
    public enum FileType {
        USER_FACE, VIDEO_FILE, VIDEO_FACE
    }

    /**
     * 保存文件并返回相对于 ResourceProperties.path 的路径
     *
     * @return 保存的文件相对路径(失败为null)
     */
    @Override
    public String save(MultipartFile file, FileType fileType) {
        if (file.isEmpty()) {
            return null;
        }

        String savePath = createSavePath(file, fileType);
        File saveFile = new File(savePath);
        if (saveFile.exists()) {
            return null;
        }
        try {
            file.transferTo(saveFile);
        } catch (IOException e) {
            logger.debug("文件保存错误: {}", saveFile);
            return null;
        }
        logger.debug("文件保存成功: {}", saveFile);
        return savePath.replace(resourceProperties.getPath() + "/", "");
    }

    /**
     * 根据 MultipartFile 的内容类型和 FileType 构造保存文件的全路径
     *
     * @return 保存的文件全路径
     */
    private String createSavePath(MultipartFile file, FileType fileType) {
        String contentType = file.getContentType();
        String saveType = "";
        if (contentType != null) {
            saveType = contentType.split("/")[1];
        }
        String fileName = UUID.randomUUID().toString().replace("-", "");
        StringBuilder savePath = new StringBuilder(resourceProperties.getPath());
        switch (fileType) {
            case USER_FACE:
                savePath.append(USER_FACE_PATH);
                break;
            case VIDEO_FACE:
                savePath.append(VIDEO_FACE_PATH);
                break;
            case VIDEO_FILE:
                savePath.append(VIDEO_FILE_PATH);
                break;
            default:
                break;
        }
        savePath.append("/");
        savePath.append(fileName);
        savePath.append(".");
        savePath.append(saveType);
        return savePath.toString();
    }
}
