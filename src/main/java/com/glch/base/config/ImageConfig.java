package com.glch.base.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class ImageConfig {

    @Value("${image.savePath}")
    private String savePath;

}
