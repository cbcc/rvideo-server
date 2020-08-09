package cn.scau.rvideo.server.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.MultipartConfigElement;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private ResourceProperties resourceProperties;
    private static final Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        logger.info("资源路径: " + resourceProperties.getPath());
        registry.addResourceHandler("/resource/**").addResourceLocations("file:" + resourceProperties.getPath() + "/");
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 单个文件最大2G
        factory.setMaxFileSize(DataSize.ofGigabytes(2));
        // 设置总上传数据总大小3G
        factory.setMaxRequestSize(DataSize.ofGigabytes(3));
        return factory.createMultipartConfig();
    }
}
