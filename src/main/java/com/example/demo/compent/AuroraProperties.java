package com.example.demo.compent;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author aluohe
 * @className AuroraProperties
 * @projectName kamo-cloud
 * @date 2020/3/26 11:17
 * @description 极光配置
 * @modified_by
 * @version: 1.0.0
 */
@Data
@Component
@ConfigurationProperties(prefix = "aurora")
public class AuroraProperties {
    private String APP_KEY;
    private String MASTER_SECRET;
}