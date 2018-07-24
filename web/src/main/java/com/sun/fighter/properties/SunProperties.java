package com.sun.fighter.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @创建人 chengyin
 * @创建时间 2018/7/24
 * @描述
 */
@Component
@ConfigurationProperties(prefix = SunProperties.PREFIX)
public class SunProperties {

    public static final String PREFIX = "sun";

    private Boolean kaptchaOpen = false;

    private Boolean swaggerOpen = false;

    private String fileUploadPath;

    private Boolean haveCreatePath = false;

    public String getFileUploadPath() {
        //如果没有写文件上传路径,保存到临时目录
        if (isEmpty(fileUploadPath)) {
            return System.getProperty("java.io.tmpdir");
        } else {
            //判断有没有结尾符,没有得加上
            if (!fileUploadPath.endsWith(File.separator)) {
                fileUploadPath = fileUploadPath + File.separator;
            }
            //判断目录存不存在,不存在得加上
            if (!haveCreatePath) {
                File file = new File(fileUploadPath);
                file.mkdirs();
                haveCreatePath = true;
            }
            return fileUploadPath;
        }
    }
}
