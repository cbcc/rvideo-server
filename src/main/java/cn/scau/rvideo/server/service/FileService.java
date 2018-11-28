package cn.scau.rvideo.server.service;

import org.springframework.lang.Nullable;
import org.springframework.web.multipart.MultipartFile;

public interface FileService<E extends Enum<E>> {
    @Nullable
    String save(MultipartFile file, E type);
}
