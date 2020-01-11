package com.cloud.oss;

import com.cloud.common.data.annotation.EnableProData;
import com.cloud.common.oss.annotation.EnableProOss;
import com.cloud.common.security.annotation.EnableProSecurtity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *   基础邮件模块
 * @author Aijm
 * @since  2019/5/8
 */
@SpringBootApplication
@EnableProData
@EnableProSecurtity
@EnableProOss
public class CloudOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudOssApplication.class, args);
    }


}
